package tongji.product.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RedemptionDTO {
    private String redState;
    @NotBlank
    private String fundNumber;
    @NotBlank
    private String cerNumber;
    private float redAmount;
    @NotBlank
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date redDate;
    @NotBlank
    private float redShare;
    @NotBlank
    private String redCardNumber;

    public String getRedState() { return redState; }
    public void  setRedState(String redState) { this.redState = redState; }

    public String getFundNumber() { return fundNumber; }
    public void setFundNumber(String fundNumber) { this.fundNumber = fundNumber; }

    public String getCerNumber() { return cerNumber; }
    public void setCerNumber(String cerNumber) { this.cerNumber = cerNumber; }

    public float getRedAmount() { return redAmount; }
    public void setRedAmount(float redAmount) { this.redAmount = redAmount;}

    public Date getRedDate() { return redDate; }
    public void setRedDate(Date redDate) { this.redDate = redDate; }

    public float getRedShare() { return redShare; }
    public void setRedShare(float redShare) { this.redShare = redShare; }

    public String getRedCardNumber() { return redCardNumber; }
    public void setRedCardNumber(String redCardNumber) { this.redCardNumber = redCardNumber; }

    public float calcRedAmount(float fundValue){
        return fundValue * redShare;
    }
}
