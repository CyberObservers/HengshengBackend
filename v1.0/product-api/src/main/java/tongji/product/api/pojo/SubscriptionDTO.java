package tongji.product.api.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SubscriptionDTO {
    private String subState;
    private String fundNumber;
    private String cerNumber;
    private Float subAmount;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date subDate;

    private Float subShare;
    private String subCardNumber;

    public String getSubState() {
        return subState;
    }

    public void setSubState(String subState) {
        this.subState = subState;
    }

    public String getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public Float getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(Float subAmount) {
        this.subAmount = subAmount;
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    public Float getSubShare() {
        return subShare;
    }

    public void setSubShare(Float subShare) {
        this.subShare = subShare;
    }

    public String getSubCardNumber() {
        return subCardNumber;
    }

    public void setSubCardNumber(String subCardNumber) {
        this.subCardNumber = subCardNumber;
    }

    public Float calculateSubShare(Float fundValue) {
        // 计算上账份额
        Float subShare = subAmount / fundValue;
        return subShare;
    }
}