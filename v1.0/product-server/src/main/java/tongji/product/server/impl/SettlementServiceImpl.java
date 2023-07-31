package tongji.product.server.impl;
import clojure.lang.IFn;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.DailyValueService;
import tongji.product.api.RedemptionService;
import tongji.product.api.RiskTraceService;
import tongji.product.api.SettlementService;
import tongji.product.api.pojo.*;
import tongji.product.server.mapper.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CloudComponent
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private DailyValueMapper dailyValueMapper;
    @Autowired
    private HoldingsMapper holdingsMapper;
    @Autowired
    private RedemptionMapper redemptionMapper;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private CardStatementMapper cardStatementMapper;

    public List<SubscriptionDTO> getUnsettledSubscriptions() {
        List<SubscriptionDTO> subscriptions = subscriptionMapper.getUnsettledSubscriptions();
        return subscriptions;
    }

    public String settlementSub(){
        List<SubscriptionDTO> unsettledSubscriptions = getUnsettledSubscriptions();
        for (SubscriptionDTO subscription : unsettledSubscriptions) {
            Date subDate = subscription.getSubDate();//获取申购记录中的日期
            String fundNumber = subscription.getFundNumber();//获取申购的基金代码



             //创建一个SimpleDateFormat对象，指定格式为"yyyy-MM-dd"
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             //调用parse方法，将subDateString转换为subDate
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String subDateString = sdf.format(subDate);
//                if(1==1){
//                    return subDateString;
//                }

                Date formattedDate = sdf.parse(subDateString);
                DailyValueDTO  dailyValue = dailyValueMapper.getOneDailyValue(fundNumber,formattedDate);//此处时间转换有问题
                if(dailyValue == null){
                    continue;
                }
                Float fundValue = dailyValue.getFundValue();//根据申购日期和基金代码查找当天该基金的净值
                if (fundValue == 0 || Math.abs(fundValue) < 0.000001) {
                    // 给fundValue一个合理的默认值，例如1
                    throw new IllegalArgumentException("fundValue cannot be zero");
                    // 或者抛出一个异常，提示fundValue不能为零
                }
                // 调用calculateSubShare方法，传入fundValue
                //subShare = subscription.calculateSubShare(fundValue);


                Float subShare = subscription.calculateSubShare(fundValue);//计算得出上账份额

                subscription.setSubShare(subShare);//设置对应份额在DTO中
                subscription.setSubState("已上账");//设置为已经上账
                subscriptionMapper.updateSubscription(subscription); //对应还要修改数据库中的表

            }
            catch (ParseException e){

            }
           //再更新持仓表
            HoldingsDTO holdings = new HoldingsDTO();
            HoldingsDTO existingHoldings = holdingsMapper.getOnlyHoldings(subscription.getSubCardNumber(), subscription.getCerNumber(),subscription.getFundNumber());
            if (existingHoldings == null) {
                holdings.setCardNumber(subscription.getSubCardNumber());
                holdings.setCerNumber(subscription.getCerNumber());
                holdings.setFundNumber(subscription.getFundNumber());
                holdings.setTotalShare(subscription.getSubShare());
                holdingsMapper.createHoldings(holdings);//如果为空则增加这个持仓表

                //return holdings.getFundNumber();
            }
            //如果对应持仓表已经存在，则更新existingHoldings
            else {
                float share = existingHoldings.getTotalShare() + subscription.getSubShare();
                existingHoldings.setTotalShare(share);
                holdingsMapper.updateHoldings(existingHoldings);
                //return holdings.getFundNumber();
            }
        }
        return "清算完成";
    }

    private List<RedemptionDTO> getUnsettledRedemptions(){
        return redemptionMapper.getUnsettledRedemption();
    }

    public String settlementRe() {
        List<RedemptionDTO> unsettledRedemption = getUnsettledRedemptions();
        for(RedemptionDTO redemption : unsettledRedemption){
            Date redDate = redemption.getRedDate();
            String fundNumber = redemption.getFundNumber();

            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String redDateString = dateFormat.format(redDate);
                Date formattedDate = dateFormat.parse(redDateString);
                DailyValueDTO dailyValue = dailyValueMapper.getOneDailyValue(fundNumber,formattedDate);
                float fundValue = dailyValue.getFundValue();
                if (fundValue == 0 || Math.abs(fundValue) < 0.000001) {
                    throw new IllegalArgumentException("fundValue cannot be zero");
                }

                float redAmount = redemption.calcRedAmount(fundValue);
                redemption.setRedAmount(redAmount);
                redemption.setRedState("已上账");

            } catch(Exception e) {
                System.out.println(e.toString());
            }

            BankCardDTO bankCard = bankCardMapper.getOneCard(redemption.getRedCardNumber(),redemption.getCerNumber());
            float preBalance = bankCard.getBalance();
            bankCard.setBalance(preBalance + redemption.getRedAmount());
            bankCardMapper.updateCard(bankCard);

            CardStatementDTO cardStatement = new CardStatementDTO();
            cardStatement.setStateDate(new Date());
            cardStatement.setFundNumber(redemption.getFundNumber());
            cardStatement.setCardNumber(redemption.getRedCardNumber());
            cardStatement.setStateAmount(redemption.getRedAmount());
            cardStatement.setStaBalance(bankCard.getBalance());
            cardStatementMapper.addCardStatement(cardStatement);
        }
        return "清算完成";
    }
}
