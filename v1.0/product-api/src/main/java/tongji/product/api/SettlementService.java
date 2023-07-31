package tongji.product.api;
import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.RiskTraceDTO;

import java.util.Date;

@CloudService(validationNull = true, validation = true)
public interface SettlementService {

    @CloudFunction("333009")
    String settlementSub();

    @CloudFunction("333010")
    String settlementRe();
}
