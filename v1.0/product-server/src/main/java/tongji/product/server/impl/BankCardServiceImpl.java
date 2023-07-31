package tongji.product.server.impl;

import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.BankCardService;
import tongji.product.api.pojo.BankCardDTO;
import tongji.product.server.mapper.BankCardMapper;

import java.util.List;


@CloudComponent
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardMapper cardMapper;

    public String addBankCard(BankCardDTO card) {
        List<BankCardDTO> existCard = cardMapper.getCard(card.getCardNumber());
        if(existCard.isEmpty()) {
            cardMapper.createBankCard(card);
            return card.getCardNumber();
        }
        cardMapper.updateCard(card);
        return card.getCardNumber();
    }

    public String removeBankCard(BankCardDTO card) {
        BankCardDTO existCard = cardMapper.getOneCard(card.getCardNumber(), card.getCerNumber());
        if(null == existCard){
            return "Target card doesn't exist.";
        }
        cardMapper.deleteCard(card.getCardNumber(), card.getCerNumber());
        return card.getCardNumber();
    }

    public String modifyBankCard(BankCardDTO card) {
        BankCardDTO existCard = cardMapper.getOneCard(card.getCardNumber(), card.getCerNumber());
        if(null == existCard){
            cardMapper.createBankCard(card);
            return card.getCardNumber();
        }
        cardMapper.updateCard(card);
        return card.getCardNumber();
    }

    public List<BankCardDTO> getBankCard(String CerNumber) {
        Assert.hasLength(CerNumber, "缺少查询的证件号");
        return cardMapper.getCard(CerNumber);
    }

    public BankCardDTO getOneBankCard(String CardNumber, String CerNumber) {
        Assert.hasLength(CardNumber, "缺少查询的银行卡号");
        Assert.hasLength(CerNumber, "缺少查询的证件号");
        return cardMapper.getOneCard(CardNumber, CerNumber);
    }
}
