package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.RiskTraceService;
import tongji.product.api.pojo.RiskTraceDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class RiskTraceController {
    @CloudReference
    private RiskTraceService riskTraceService;

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/createRiskTrace", method = RequestMethod.GET)
    public String createRiskTrace(@RequestParam(value = "fund_number", required = true) String fundNumber,
                           @RequestParam(value = "cer_number",  required = true) String cerNumber
                           /*@RequestParam(value = "red_date",    required = true) Date date*/){
        RiskTraceDTO riskTrace = new RiskTraceDTO();
        riskTrace.setCerNumber(cerNumber);
        riskTrace.setFundNumber(fundNumber);
        Date nowDate = new Date();
        riskTrace.setRedDate(nowDate);
        return riskTraceService.createRiskTrace(riskTrace);
    }
}
