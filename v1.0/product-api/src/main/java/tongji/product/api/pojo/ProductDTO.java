package tongji.product.api.pojo;


import javax.validation.constraints.NotBlank;

public class ProductDTO {
    @NotBlank
    private String fundNumber;
    @NotBlank
    private String fundName;
    @NotBlank
    private String fundType;
    private int fundRisk;

    public String getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public int getFundRisk() {
        return fundRisk;
    }

    public void setFundRisk(int fundRisk) {
        this.fundRisk = fundRisk;
    }
}
