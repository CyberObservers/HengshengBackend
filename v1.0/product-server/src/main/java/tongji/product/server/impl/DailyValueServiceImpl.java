package tongji.product.server.impl;


import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.mysql.cj.exceptions.DataTruncationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.DailyValueService;
import tongji.product.api.ProductService;
import tongji.product.api.pojo.DailyValueChanges;
import tongji.product.api.pojo.DailyValueDTO;
import tongji.product.api.pojo.ProductDTO;
import tongji.product.api.pojo.SubscriptionDTO;
import tongji.product.server.mapper.DailyValueMapper;
import tongji.product.server.mapper.ProductMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@CloudComponent
public class DailyValueServiceImpl implements DailyValueService {
    @Autowired

    private DailyValueMapper dailyValueMapper;

    @Autowired
    private ProductMapper productMapper;

    public String createDailyValue(DailyValueDTO dailyValue){
        DailyValueDTO existDailyValue = dailyValueMapper.getOneDailyValue(dailyValue.getFundNumber(), dailyValue.getFundDate());
        if(existDailyValue == null){
            dailyValueMapper.createDailyValue(dailyValue);
            return dailyValue.getFundNumber();
        }
        throw new IllegalArgumentException("已存在相同的基金代码和日期的日净值");
    }

    public String updateDailyValue(DailyValueDTO dailyValue){
        DailyValueDTO existDailyValue = dailyValueMapper.getOneDailyValue(dailyValue.getFundNumber(), dailyValue.getFundDate());
        if(existDailyValue == null){ throw new IllegalArgumentException("不存在相同的基金代码和日期的日净值"); }
        dailyValueMapper.updateDailyValue(dailyValue);
        return dailyValue.getFundNumber();
    }

    public List<DailyValueDTO> getDailyValue(String fundNumber/*, Date date*/){
        Assert.hasLength(fundNumber, "缺少查询的基金代码");
        /*Assert.hasLength(String.valueOf(date), "缺少查询的日期");*/
        return dailyValueMapper.getDailyValue(fundNumber/*, date*/);
    }

    public List<DailyValueChanges>getDailyValueByDate(Date date){
        // 获取净值与产品列表
        List<DailyValueDTO> dailyValueByDate = dailyValueMapper.getDailyValueByDate(date);
        List<ProductDTO> allProduct = productMapper.getAllProduct();

        // 创建更改数组
        List<DailyValueChanges> changes = new LinkedList<DailyValueChanges>();

        for(DailyValueDTO value : dailyValueByDate){
            for(ProductDTO product : allProduct){
                if(product.getFundNumber().equals(value.getFundNumber())){
                    // 相同，生成change对象，压入change的list，循环结束
                    DailyValueChanges dailyValueChanges = new DailyValueChanges();
                    dailyValueChanges.setDailyValue(value);
                    dailyValueChanges.setFundName(product.getFundName());
                    changes.add(dailyValueChanges);
                    break;
                }
            }
        }

        return changes;
    }

    public DailyValueDTO getOneDailyValue(String fundNumber, Date fundDate) {
        Assert.hasLength(fundNumber, "缺少查询的基金代码");
        Assert.hasLength(String.valueOf(fundDate), "缺少查询的日期");
        return dailyValueMapper.getOneDailyValue(fundNumber, fundDate);
    }

    public  String updateAllDailyValue(Date currentDate,Date lastDate){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastDateString = dateFormat.format(lastDate);
            String curDateString = dateFormat.format(currentDate);

            Date formattedLastDate = dateFormat.parse(lastDateString);
            Date formattedCurDate = dateFormat.parse(curDateString);

            System.out.println(currentDate);
            System.out.println(lastDate);

            List<ProductDTO> products = productMapper.getAllProduct();
            for(ProductDTO product :products){
                DailyValueDTO dailyValue = new DailyValueDTO();
                String fundNumber = product.getFundNumber();
                DailyValueDTO lastDailyValue = dailyValueMapper.getOneDailyValue(fundNumber,formattedLastDate);
                Float lastValue;
                if(lastDailyValue ==null){
                    lastValue = 1.0f;
                }
                else{
                    lastValue = lastDailyValue.getFundValue();
                }
                Double num = 0.9 + Math.random() * 0.2;

                Float newFundValue =lastValue *num.floatValue();
                dailyValue.setFundValue(newFundValue);
                dailyValue.setFundNumber(fundNumber);
                dailyValue.setFundDate(currentDate);


                DailyValueDTO existDailyValue = dailyValueMapper.getOneDailyValue(fundNumber, formattedCurDate);
                if(null == existDailyValue){
                    dailyValueMapper.createDailyValue(dailyValue);
                } else {
                    existDailyValue.setFundValue(newFundValue);
                    dailyValueMapper.updateDailyValue(existDailyValue);
                }
            }
            return "完成日净值更新";
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return "出现问题";

    }

}
