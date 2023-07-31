package tongji.product.server.impl;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tongji.product.api.BankCardService;
import tongji.product.api.SubscriptionService;
import tongji.product.api.pojo.BankCardDTO;
import tongji.product.api.pojo.CardStatementDTO;
import tongji.product.api.pojo.SubscriptionDTO;
import tongji.product.server.mapper.BankCardMapper;
import tongji.product.server.mapper.CardStatementMapper;
import tongji.product.server.mapper.SubscriptionMapper;


import java.util.List;

@CloudComponent
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private BankCardMapper cardMapper;

    @Autowired
    private CardStatementMapper cardStatementMapper;

    public String createSubscription(SubscriptionDTO subscription) {

        //BankCardDTO bankCard=new BankCardDTO();//更新对应银行卡的余额
        subscriptionMapper.createSubscription(subscription);
        Float coast=subscription.getSubAmount();//获取应该被扣除的费用
        CardStatementDTO cardStatement = new CardStatementDTO();//增加银行卡流水

        BankCardDTO bankCard = cardMapper.getOneCard(subscription.getSubCardNumber(), subscription.getCerNumber());//根据申购订单中的银行卡号和身份证号获取这张银行卡
        bankCard.setBalance(bankCard.getBalance()-coast);//扣除相应金额
        cardMapper.updateCard(bankCard);//更新数据库中的银行卡信息

        cardStatement.setCardNumber(subscription.getSubCardNumber());
        cardStatement.setFundNumber(subscription.getFundNumber());
        cardStatement.setStaBalance(bankCard.getBalance());
        cardStatement.setStateAmount(-coast);
        cardStatement.setStateDate(subscription.getSubDate());
        cardStatementMapper.addCardStatement(cardStatement);//添加银行卡流水



        return "成功为用户"+subscription.getCerNumber()+"申购"+subscription.getFundNumber()+"基金";
    }

    public String updateSubscription(SubscriptionDTO subscription) {

        return "已确认";
    }

}