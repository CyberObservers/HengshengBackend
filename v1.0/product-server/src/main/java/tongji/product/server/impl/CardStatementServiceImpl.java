package tongji.product.server.impl;


import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.CardStatementService;
import tongji.product.api.pojo.CardStatementDTO;
import tongji.product.server.mapper.CardStatementMapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CloudComponent
public class CardStatementServiceImpl implements CardStatementService {
    @Autowired
    private CardStatementMapper cardStatementMapper;

    public String createCardStatement(CardStatementDTO cardStatement){
        CardStatementDTO existCardStatement = cardStatementMapper.getCardStatement(
                cardStatement.getCardNumber(),
                cardStatement.getFundNumber(),
                cardStatement.getStateDate());
        if(null == existCardStatement){
            cardStatementMapper.addCardStatement(cardStatement);
            return cardStatement.getCardNumber();
        }
        throw new IllegalArgumentException("已存在相同的银行卡流水");
    }

    public List<CardStatementDTO> getCardStatementViaFund(String cardNumber, String fundNumber) {
        Assert.hasLength(cardNumber, "缺少银行卡号");
        Assert.hasLength(fundNumber, "缺少基金代码");
        return cardStatementMapper.getCardStatementViaFund(cardNumber, fundNumber);
    }

    public List<CardStatementDTO> getCardStatementViaDate(String cardNumber, Date date) {
        Assert.hasLength(cardNumber, "缺少银行卡号");
        Assert.notNull(date, "缺少日期");
        return cardStatementMapper.getCardStatementViaDate(cardNumber, (Timestamp) date);
    }

    public CardStatementDTO getCardStatement(String cardNumber, String fundNumber, Date date) {
        Assert.hasLength(cardNumber, "缺少银行卡号");
        Assert.hasLength(fundNumber, "缺少基金代码");
        Assert.notNull(date, "缺少日期");
        return cardStatementMapper.getCardStatement(cardNumber, fundNumber, (Timestamp) date);
    }

}
