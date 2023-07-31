package tongji.product.api.pojo;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class BankCardDTO {
    @NotBlank
    private String bankName;
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String cerNumber;//certification number
    @NotNull
    private Float balance;

    // Getters and Setters
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

}

