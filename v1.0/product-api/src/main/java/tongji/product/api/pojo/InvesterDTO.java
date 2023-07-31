package tongji.product.api.pojo;
import javax.validation.constraints.NotBlank;

public class InvesterDTO {
    @NotBlank
    private String userType;
    @NotBlank
    private String userName;
    @NotBlank
    private String cerType;//证件类型
    @NotBlank
    private String cerNumber;//证件号码
    private int riskGrade;
    private boolean available;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCerType() {
        return cerType;
    }

    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public int getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(int riskGrade) {
        this.riskGrade = riskGrade;
    }

    public boolean getAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

}
