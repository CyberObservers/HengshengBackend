package tongji.product.api.pojo;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class DailyValueDTO {
    private String fundNumber;
    @NotBlank
    private float fundValue;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fundDate;

    public String getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber;
    }

    public float getFundValue() {
        return fundValue;
    }

    public void setFundValue(Float fundValue) {
        this.fundValue = fundValue;
    }

    public Date getFundDate() {
        return fundDate;
    }

    public void setFundDate(Date fundDate) {
        this.fundDate = fundDate;
    }


}
