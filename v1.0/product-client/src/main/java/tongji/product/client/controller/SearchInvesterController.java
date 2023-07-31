package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;
import tongji.product.api.InvesterService;
import tongji.product.api.pojo.InvesterDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class SearchInvesterController {
    @CloudReference
    private InvesterService investerService;

    @RequestMapping(value = "/search/invester/name", method = RequestMethod.GET)
    List<InvesterDTO> searchInvesterViaName(@RequestParam(value = "key", required = true) String key){
        return investerService.searchInvesterViaName(key);
    }

    @RequestMapping(value = "/search/invester/number", method = RequestMethod.GET)
    List<InvesterDTO> searchInvesterViaNumber(@RequestParam(value = "key", required = true) String key){
        return investerService.searchInvesterViaNumber(key);
    }
}
