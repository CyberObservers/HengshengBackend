package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.CardStatementService;
import tongji.product.api.pojo.CardStatementDTO;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class CardStatementController {
    @CloudReference
    private CardStatementService cardStatementService;

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/createCardStatement", method = RequestMethod.POST)
    public String createCardStatement(@RequestParam(value = "card_number") String cardNumber,
//                                    @RequestParam(value = "state_date") Timestamp stateDate,
                                    @RequestParam(value = "state_amount") float stateAmount,
                                    @RequestParam(value = "fund_number") String fundNumber,
                                    @RequestParam(value = "sta_balance") float staBalance){
        CardStatementDTO cardStatement = new CardStatementDTO();
        cardStatement.setCardNumber(cardNumber);
        Date nowDate = new Date();
        cardStatement.setStateDate(nowDate);
        cardStatement.setStateAmount(stateAmount);
        cardStatement.setFundNumber(fundNumber);
        cardStatement.setStaBalance(staBalance);

        // return cardStatement.getStateDate();
        return cardStatementService.createCardStatement(cardStatement);
    }

    @RequestMapping(value = "/getCardStatement/fund", method = RequestMethod.GET)
    public List<CardStatementDTO> getCardStatementViaFund(@RequestParam(value = "card_number", required = true) String cardNumber,
                                                          @RequestParam(value = "fund_number", required = true) String fundNumber){
        return cardStatementService.getCardStatementViaFund(cardNumber,fundNumber);
    }

    @RequestMapping(value = "/getCardStatement/date", method = RequestMethod.GET)
    public List<CardStatementDTO> getCardStatementViaDate(@RequestParam(value = "card_number", required = true) String cardNumber,
                                                          @RequestParam(value = "state_date", required = true) Date stateDate){
        return cardStatementService.getCardStatementViaDate(cardNumber,stateDate);
    }

    @RequestMapping(value = "/getCardStatement/one", method = RequestMethod.GET)
    public CardStatementDTO getCardStatement(@RequestParam(value = "card_number", required = true) String cardNumber,
                                             @RequestParam(value = "fund_number", required = true) String fundNumber,
                                             @RequestParam(value = "state_date", required = true) Date stateDate){
        return cardStatementService.getCardStatement(cardNumber,fundNumber,stateDate);
    }

}
