package tongji.product.client.controller;


import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.HoldingsService;
import tongji.product.api.pojo.HoldingsDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class HoldingsController {
    @CloudReference
    HoldingsService holdingsService;

    @RequestMapping(value = "/getHoldingsByCerNumber",method = RequestMethod.GET)
    public List<HoldingsDTO>getHoldingsByCerNumber(@RequestParam("cer_number")String cerNumber){
        return holdingsService.getHoldingsByCerNumber(cerNumber);
    }
}
