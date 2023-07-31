package tongji.product.api;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.SubscriptionDTO;


import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface SubscriptionService {

    @CloudFunction("0301")
    String createSubscription(SubscriptionDTO subscription);

    @CloudFunction("0302")
    String updateSubscription(SubscriptionDTO subscription);
}
