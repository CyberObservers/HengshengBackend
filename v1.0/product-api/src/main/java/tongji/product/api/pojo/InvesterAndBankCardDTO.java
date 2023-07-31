package tongji.product.api.pojo;

import java.util.List;

public class InvesterAndBankCardDTO {
    private  InvesterDTO invester;
    private List<BankCardDTO> bankCards;


    public InvesterDTO getInvester() { return invester; }
    public void setInvester(InvesterDTO invester) { this.invester = invester; }
    public List<BankCardDTO> getBankCards() { return bankCards; }
    public void setBankCards(List<BankCardDTO> bankCards) { this.bankCards = bankCards; }
}
