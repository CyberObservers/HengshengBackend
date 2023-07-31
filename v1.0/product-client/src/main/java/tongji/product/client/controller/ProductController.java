package tongji.product.client.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
//<<<<<<< Updated upstream
import tongji.product.api.*;
import tongji.product.api.pojo.*;

import java.text.SimpleDateFormat;
import java.util.List;
import tongji.product.api.pojo.ProductDTO;
import java.sql.Date;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class ProductController {

    @CloudReference
    private ProductService productService;

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private DailyValueService dailyValueService;

    @CloudReference
    private InvesterService investerService;

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String createProduct(@RequestParam(value = "fund_number", required = false) String fundNumber,
                                @RequestParam(value = "fund_name") String fundName,
                                @RequestParam(value = "fund_type", required = false) String fundType,
                                @RequestParam(value = "fund_risk",required = false )int fundRisk){

        ProductDTO product = new ProductDTO();
        product.setFundNumber(fundNumber);
        product.setFundName(fundName);
        product.setFundType(fundType);
        product.setFundRisk(fundRisk);
        return productService.createProduct(product);
    }
    @RequestMapping(path = "/getProduct", method = RequestMethod.GET)
    public ProductDTO getProduct(@RequestParam(value = "fund_number") String fundNumber){
        return productService.getProduct(fundNumber);
    }

    @RequestMapping(value = "/modifyProduct", method = RequestMethod.PATCH)
    public String modifyProduct(@RequestParam(value = "fund_number") String fundNumber,
                                @RequestParam(value = "fund_name") String fundName,
                                @RequestParam(value = "fund_type") String fundType,
                                @RequestParam(value = "fund_risk" )int fundRisk){
        ProductDTO product = new ProductDTO();
        product.setFundNumber(fundNumber);
        product.setFundName(fundName);
        product.setFundType(fundType);
        product.setFundRisk(fundRisk);
        return productService.modifyProduct(product);
    }

    @RequestMapping(value = "/deleteProduct",method = RequestMethod.DELETE)
    public String deleteProduct(@RequestParam(value = "fund_number")String fundNumber){
        return productService.deleteProduct(fundNumber);
    }

    @RequestMapping(value = "/createDailyValue",method = RequestMethod.POST)
    public String createDailyValue(@RequestParam(value = "fund_number") String fundNumber,
                                   @RequestParam(value = "fund_value") float fundValue,
                                   @RequestParam(value = "fund_date") java.sql.Date fundDate) {
        DailyValueDTO dailyValue = new DailyValueDTO();
        dailyValue.setFundNumber(fundNumber);
        dailyValue.setFundValue(fundValue);
        dailyValue.setFundDate(fundDate);
        return dailyValueService.createDailyValue(dailyValue);
    }
//    @RequestMapping(path = "/updateDailyValue", method = RequestMethod.GET)
//    public String updateDailyValue(@RequestParam(value = "fund_number") String fundNumber,
//                                   @RequestParam(value = "fund_value") float fundValue,
//                                   @RequestParam(value = "fund_date") Date fundDate){
//        DailyValueDTO dailyValue = new DailyValueDTO();
//        dailyValue.setFundNumber(fundNumber);
//        dailyValue.setFundValue(fundValue);
//        dailyValue.setFundDate(fundDate);
//        return dailyValueService.updateDailyValue(dailyValue);
//    }

    @RequestMapping(path = "/getDailyValue", method = RequestMethod.GET)
    public List<DailyValueDTO> getDailyValue(@RequestParam(value = "fund_number") String fundNumber
            /*@RequestParam(value = "date") Date date*/){
        return dailyValueService.getDailyValue(fundNumber/*, date*/);
    }

    @RequestMapping(path ="/getDailyValueByDate",method = RequestMethod.GET)
    public List<DailyValueChanges> getDailyValueByDate(@RequestParam(value = "fund_date")java.util.Date fundDate){
        return dailyValueService.getDailyValueByDate(fundDate);
    }

    @RequestMapping(path = "/getAllProduct",method = RequestMethod.GET)
    public  List<ProductDTO>getAllProduct(){
        return productService.getAllProduct();
    }

}
