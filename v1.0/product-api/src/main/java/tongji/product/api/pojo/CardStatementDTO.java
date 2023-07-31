package tongji.product.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CardStatementDTO {
    @NotBlank
    private String cardNumber;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date stateDate;
    @NotBlank
    private float stateAmount;
    @NotBlank
    private String fundNumber;
    private float staBalance;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Date getStateDate() { return stateDate; }
    public void setStateDate(Date stateDate) { this.stateDate = stateDate; }

    public float getStateAmount() { return stateAmount; }
    public void setStateAmount(float stateAmount) { this.stateAmount = stateAmount; }

    public String getFundNumber() { return fundNumber; }
    public void setFundNumber(String fundNumber) { this.fundNumber = fundNumber; }

    public float getStaBalance() {return staBalance; }
    public void setStaBalance(float staBalance) { this.staBalance = staBalance; }
}
