package tongji.product.api;


import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.InvesterDTO;

import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface InvesterService {
    @CloudFunction("0202")
    String createInvester(InvesterDTO invester);

    @CloudFunction("0203")
    InvesterDTO getInvester(String cerNumber);

    @CloudFunction("0204")
    List<InvesterDTO> getAllInvester();

    @CloudFunction("0205")
    List<InvesterDTO> searchInvesterViaName(String keyWord);

    @CloudFunction("0206")
    List<InvesterDTO> searchInvesterViaNumber(String keyWord);

    @CloudFunction("0207")
    String deleteInvester(String cerNumber);

    @CloudFunction("0208")
    String updateInvester(InvesterDTO invester);

    @CloudFunction("0209")
    InvesterDTO getAvailableInvester(String cerNumber);
}
