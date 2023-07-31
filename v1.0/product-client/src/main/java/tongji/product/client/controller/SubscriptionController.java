package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.BankCardService;
import tongji.product.api.InvesterService;
import tongji.product.api.SettlementService;
import tongji.product.api.SubscriptionService;
import tongji.product.api.pojo.BankCardDTO;
import tongji.product.api.pojo.InvesterAndBankCardDTO;
import tongji.product.api.pojo.InvesterDTO;
import tongji.product.api.pojo.SubscriptionDTO;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class SubscriptionController {

    @CloudReference
    private SubscriptionService subscriptionService;

    @CloudReference
    private InvesterService investerService;

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private SettlementService settlementService;

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat,true));
    }

    @RequestMapping(path = "/getInvesterAndCards",method = RequestMethod.GET)
    public ResponseEntity<InvesterAndBankCardDTO> getInvesterAndCards(@RequestParam(value = "cer_number")String cerNumber){
        InvesterDTO invester=investerService.getAvailableInvester(cerNumber);
        List<BankCardDTO> bankCards= bankCardService.getBankCard(cerNumber);
        InvesterAndBankCardDTO investerAndBankCard = new InvesterAndBankCardDTO();
        investerAndBankCard.setInvester(invester);
        investerAndBankCard.setBankCards(bankCards);
        return ResponseEntity.ok(investerAndBankCard);
    }

    @RequestMapping(path = "/createSubscription",method = RequestMethod.POST)
    public String createSubscription(@RequestParam(value = "cer_number")String cerNumber,
                                     @RequestParam(value = "sub_card_number")String subCardNumber,
                                     @RequestParam(value = "fund_number")String fundNumber,
                                     @RequestParam(value = "sub_amount")float subAmount){
//        DailyValueDTO dailyValue = new DailyValueDTO();
//        dailyValue.setFundNumber(fundNumber);
//        dailyValue.setFundValue(fundValue);
//        dailyValue.setFundDate(fundDate);
        String subState = "待确认";
        Float zeroShare = 0.0f;//待确认时，上账份额为0
        Date subDate = new Date();
        SubscriptionDTO subscription =new SubscriptionDTO();
        subscription.setSubState(subState);
        subscription.setFundNumber(fundNumber);
        subscription.setCerNumber(cerNumber);
        subscription.setSubAmount(subAmount);
        subscription.setSubDate(subDate);
        subscription.setSubShare(zeroShare);
        subscription.setSubCardNumber(subCardNumber);
        return subscriptionService.createSubscription(subscription);
    }

    @RequestMapping(path = "/settlement/sub",method = RequestMethod.PATCH)
    public String settlement(){
        return settlementService.settlementSub();
    }


}
