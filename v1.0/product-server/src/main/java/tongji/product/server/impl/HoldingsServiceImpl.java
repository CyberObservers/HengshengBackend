package tongji.product.server.impl;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tongji.product.api.HoldingsService;
import tongji.product.api.pojo.HoldingsDTO;
import tongji.product.api.pojo.ProductDTO;
import tongji.product.server.mapper.HoldingsMapper;
import tongji.product.server.mapper.ProductMapper;

import java.util.List;
@CloudComponent
public class HoldingsServiceImpl implements HoldingsService {

    @Autowired
    private HoldingsMapper holdingsMapper;
    @Autowired
    private ProductMapper productMapper;

    public String createInvestorHoldings(HoldingsDTO holdings) {
        List<HoldingsDTO> existingHoldings = holdingsMapper.getHoldings(holdings.getCerNumber());
        if (existingHoldings.isEmpty()) {
            holdingsMapper.createHoldings(holdings);
            return holdings.getFundNumber();
        }
        throw new IllegalArgumentException("已存在相同的证件号的投资者");
    }

    public HoldingsDTO getInvestorHoldings(String fundNumber, String cerNumber, String cardNumber) {
        Assert.hasLength(fundNumber, "缺少查询的产品代码");
        return holdingsMapper.getOneHoldings(cardNumber, cerNumber);
    }

    public int deleteInvestorHoldings() {
        return holdingsMapper.deleteHoldings();
    }

    public String updateInvestorHoldings(HoldingsDTO holdings) {
        HoldingsDTO existingHoldings = holdingsMapper.getOneHoldings(holdings.getCardNumber(), holdings.getCerNumber());
        if (existingHoldings == null) {
            holdingsMapper.createHoldings(holdings);//如果为空则增加这个持仓表
            return holdings.getFundNumber();
        }
        holdingsMapper.updateHoldings(holdings);
        return holdings.getFundNumber();
    }

    public String checkHoldings(HoldingsDTO holdings){
        HoldingsDTO existShare = holdingsMapper.getOneHoldings(holdings.getCardNumber(),holdings.getCerNumber());
        ProductDTO existProduct = productMapper.getProduct(holdings.getFundNumber());
        if(null == existShare){
            return "不存在对应银行卡持仓记录";
        }
        if(null == existProduct){
            return "不存在对应基金产品";
        }
        if(existShare.getTotalShare() < holdings.getTotalShare()){
            return "持仓份额小于赎回份额";
        }
        return "OK";
    }

    public List<HoldingsDTO> getHoldingsByCerNumber(String cerNumber){
        return holdingsMapper.getHoldings(cerNumber);
    }


}
