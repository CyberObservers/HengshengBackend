package tongji.product.api;


import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.DailyValueChanges;
import tongji.product.api.pojo.DailyValueDTO;
import tongji.product.api.pojo.ProductDTO;

import java.util.Date;

import java.util.List;


@CloudService(validationNull = true, validation = true)
public interface DailyValueService {

    @CloudFunction("0502")
    String createDailyValue(DailyValueDTO dailyValue);

    @CloudFunction("05010")
    String updateDailyValue(DailyValueDTO dailyValue);

    @CloudFunction("05011")
    List<DailyValueDTO> getDailyValue(String fundNumber/*, Date date*/);


    @CloudFunction("05012")
    DailyValueDTO getOneDailyValue(String fundNumber, Date funDate);

    @CloudFunction("05013")
    String updateAllDailyValue(Date currentDate,Date lastDate);

    @CloudFunction("05014")
    List<DailyValueChanges>getDailyValueByDate(Date date);

}
