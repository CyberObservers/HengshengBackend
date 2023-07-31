package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.HoldingsService;
import tongji.product.api.RedemptionService;
import tongji.product.api.SettlementService;
import tongji.product.api.pojo.HoldingsDTO;
import tongji.product.api.pojo.RedemptionDTO;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class RedemptionController {
    @CloudReference
    private RedemptionService redemptionService;

    @CloudReference
    private HoldingsService holdingsService;

    @CloudReference
    private SettlementService settlementService;

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/createRedemption", method = RequestMethod.GET)
    public String createRedemption(@RequestParam(value = "red_state", required = false) String redState,
                                   @RequestParam(value = "fund_number", required = true) String fundNumber,
                                   @RequestParam(value = "cer_number", required = true) String cerNumber,
                                   /*@RequestParam(value = "red_date", required = true) Date redDate,*/
                                   @RequestParam(value = "red_share", required = true) float redShare,
                                   @RequestParam(value = "red_card_number", required = true) String redCardNumber) {
        RedemptionDTO redemption = new RedemptionDTO();
        redemption.setRedState((redState == null? "待确认":redState));
        redemption.setFundNumber(fundNumber);
        redemption.setCerNumber(cerNumber);
        //redemption.setRedAmount(redAmount);
        Date redDate = new Date();
        System.out.println(redDate);
        redemption.setRedDate(redDate);
        redemption.setRedShare(redShare);
        redemption.setRedCardNumber(redCardNumber);

        HoldingsDTO holdings = new HoldingsDTO();
        holdings.setCardNumber(redCardNumber);
        holdings.setCerNumber(cerNumber);
        holdings.setFundNumber(fundNumber);
        holdings.setTotalShare(redShare);

        String ret = holdingsService.checkHoldings(holdings);
        if(ret.equals("OK")){
            HoldingsDTO existShare = holdingsService.getInvestorHoldings(holdings.getFundNumber(),holdings.getCerNumber(),holdings.getCardNumber());
            float preShare = existShare.getTotalShare();
            existShare.setTotalShare(preShare - redShare);
            holdingsService.updateInvestorHoldings(existShare);
            return ret + ": " + redemptionService.createRedemption(redemption);
        }
        else{
            return ret;
        }
    }

    @RequestMapping(value = "/deleteRedemption", method = RequestMethod.GET)
    public String deleteRedemption(@RequestParam(value = "red_state", required = true) String redState,
                                   @RequestParam(value = "fund_number", required = true) String fundNumber,
                                   @RequestParam(value = "cer_number", required = true) String cerNumber,
                                   @RequestParam(value = "red_amount", required = true) float redAmount,
                                   /*@RequestParam(value = "red_date", required = true) Date redDate,*/
                                   @RequestParam(value = "red_share", required = true) float redShare,
                                   @RequestParam(value = "red_card_number", required = true) String redCardNumber){
        RedemptionDTO redemption = new RedemptionDTO();
        redemption.setRedState(redState);
        redemption.setFundNumber(fundNumber);
        redemption.setCerNumber(cerNumber);
        redemption.setRedAmount(redAmount);
        /*redemption.setRedDate(redDate);*/
        redemption.setRedShare(redShare);
        redemption.setRedCardNumber(redCardNumber);
        return redemptionService.deleteRedemption(redemption);
    }

    @RequestMapping(value = "/getRedemption/all", method = RequestMethod.GET)
    public List<RedemptionDTO> getRedemption(@RequestParam(value = "cer_number") String cerNumber){
        return redemptionService.getRedemption(cerNumber);
    }

    @RequestMapping(value = "/getRedemption/one", method = RequestMethod.GET)
    public RedemptionDTO getOneRedemption(@RequestParam(value = "cer_number") String cerNumber,
                                          @RequestParam(value = "fund_number") String fundNumber,
                                          @RequestParam(value = "red_date", required = true) Date redDate,
                                          @RequestParam(value = "red_card_number") String redCardNumber){
        return redemptionService.getOneRedemption(cerNumber, fundNumber, redDate, redCardNumber);
    }

    @RequestMapping(path = "/settlement/red",method = RequestMethod.PATCH)
    public String settlement(){
        return settlementService.settlementRe();
    }

}
