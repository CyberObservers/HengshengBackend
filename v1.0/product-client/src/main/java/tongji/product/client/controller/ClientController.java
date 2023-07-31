package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//<<<<<<< Updated upstream
import tongji.product.api.*;
import tongji.product.api.pojo.*;

import java.util.List;
//=======
import tongji.product.api.pojo.ProductDTO;

import java.sql.Date;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class ClientController {

    @CloudReference
    private ProductService productService;

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private DailyValueService dailyValueService;

    @CloudReference
    private InvesterService investerService;



//    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
//    public String sayHello(){
//        return "hello postman";
//    }

//<<<<<<< Updated upstream
    @RequestMapping(path = "/getBankCard/all", method = RequestMethod.GET)
    public List<BankCardDTO> getBankCard(@RequestParam(value = "cer_number") String cerNumber){
        return bankCardService.getBankCard(cerNumber);
    }

    @RequestMapping(path = "/getBankCard/one", method = RequestMethod.GET)
    public BankCardDTO getOneBankCard(@RequestParam(value = "card_number") String cardNumber,
                                      @RequestParam(value = "cer_number") String cerNumber){
        return bankCardService.getOneBankCard(cardNumber, cerNumber);
    }

    @RequestMapping(path = "/addBankCard", method = RequestMethod.POST)
    public String addBankCard(@RequestParam(value = "card_number") String cardNumber,
                              @RequestParam(value = "cer_number") String cerNumber,
                              @RequestParam(value = "bank_name") String bankName,
                              @RequestParam(value = "balance") float balance){
        BankCardDTO card = new BankCardDTO();
        card.setBalance(balance);
        card.setBankName(bankName);
        card.setCardNumber(cardNumber);
        card.setCerNumber(cerNumber);
        return bankCardService.addBankCard(card);
    }

    @RequestMapping(path = "/deleteBankCard", method = RequestMethod.DELETE)
    public String deleteBankCard(@RequestParam(value = "card_number") String cardNumber,
                                 @RequestParam(value = "cer_number") String cerNumber,
                                 @RequestParam(value = "bank_name",required = false) String bankName,
                                 @RequestParam(value = "balance",required = false) float balance){
        BankCardDTO card = new BankCardDTO();
        card.setBalance(balance);
        card.setBankName(bankName);
        card.setCardNumber(cardNumber);
        card.setCerNumber(cerNumber);
        return bankCardService.removeBankCard(card);
    }

    @RequestMapping(path = "/modifyBankCard", method = RequestMethod.PATCH)
    public String modifyBankCard(@RequestParam(value = "card_number") String cardNumber,
                                 @RequestParam(value = "cer_number") String cerNumber,
                                 @RequestParam(value = "bank_name") String bankName,
                                 @RequestParam(value = "balance") float balance) {
        BankCardDTO card = new BankCardDTO();
        card.setBalance(balance);
        card.setBankName(bankName);
        card.setCardNumber(cardNumber);
        card.setCerNumber(cerNumber);
        return bankCardService.modifyBankCard(card);
    }


    @RequestMapping(value = "/createInvester",method = RequestMethod.POST)
    public String createInvester(@RequestParam(value = "user_type") String userType,
                                 @RequestParam(value = "user_name") String userName,
                                 @RequestParam(value = "cer_type") String cerType,
                                 @RequestParam(value = "cer_number")String cerNumber,
                                 @RequestParam(value = "risk_grade",required = false )int riskGrade){
        InvesterDTO invester = new InvesterDTO();
        invester.setCerNumber(cerNumber);
        invester.setUserType(userType);
        invester.setUserName(userName);
        invester.setCerType(cerType);
        invester.setRiskGrade(riskGrade);
        invester.setAvailable(true);
        return investerService.createInvester(invester);

    }

    @RequestMapping(path = "/getInvester", method = RequestMethod.GET)
    public InvesterDTO getInvester(@RequestParam(value = "cer_number")String cerNumber){
        return investerService.getInvester(cerNumber);
    }

    @RequestMapping(path = "/getInvester/all", method = RequestMethod.GET)
    public List<InvesterDTO> getAllInvester(){
        return investerService.getAllInvester();
    }

    @RequestMapping(path =  "/deleteInvester",method = RequestMethod.DELETE)
    public String deleteInvester(@RequestParam(value = "cer_number")String cerNumber){
        return investerService.deleteInvester(cerNumber);
    }

    @RequestMapping(value = "/modifyInvester", method = RequestMethod.PATCH)
    public String modifyInvester(@RequestParam(value = "user_type") String userType,
                                @RequestParam(value = "user_name") String userName,
                                @RequestParam(value = "cer_type") String cerType,
                                @RequestParam(value = "cer_number" )String cerNumber,
                                @RequestParam(value = "risk_grade")int riskGrade){
        InvesterDTO invester = new InvesterDTO();
        invester.setRiskGrade(riskGrade);
        invester.setCerType(cerType);
        invester.setUserName(userName);
        invester.setUserType(userType);
        invester.setCerNumber(cerNumber);
        return investerService.updateInvester(invester);

    }



}
