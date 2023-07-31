package tongji.product.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class RiskTraceDTO {
    @NotBlank
    private String fundNumber;
    @NotBlank
    private String cerNumber;
    @NotBlank
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date redDate;

    public String getFundNumber() { return fundNumber; }

    public void setFundNumber(String fundNumber) { this.fundNumber = fundNumber; }

    public String getCerNumber() { return cerNumber; }

    public void setCerNumber(String cerNumber) { this.cerNumber = cerNumber; }

    public Date getRedDate() { return redDate; }

    public void setRedDate(Date redDate) { this.redDate = redDate; }
}
