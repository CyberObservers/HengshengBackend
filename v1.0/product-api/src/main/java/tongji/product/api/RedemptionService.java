package tongji.product.api;

import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.RedemptionDTO;

import java.util.Date;
import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface RedemptionService {
    @CloudFunction("040101")
    String createRedemption(RedemptionDTO  redemption);

    @CloudFunction("040102")
    String deleteRedemption(RedemptionDTO redemption);

    @CloudFunction("040103")
    List<RedemptionDTO> getRedemption(String cerNumber);

    @CloudFunction("040104")
    RedemptionDTO getOneRedemption(String cerNumber, String fundNumber, Date redDate, String redCardNumber);

}
