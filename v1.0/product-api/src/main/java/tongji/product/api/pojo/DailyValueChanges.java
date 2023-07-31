package tongji.product.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class DailyValueChanges {


    private String fundName;
    private DailyValueDTO dailyValue;



    public String getFundName() { return fundName; }
    public void setFundName(String fundName){ this.fundName = fundName; }

    public DailyValueDTO getDailyValue() { return dailyValue; }
    public void setDailyValue(DailyValueDTO dailyValue) { this.dailyValue = dailyValue; }
}
