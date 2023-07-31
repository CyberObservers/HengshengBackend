package tongji.product.api;

import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.BankCardDTO;

import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface BankCardService {

    @CloudFunction("020101")
    String addBankCard(BankCardDTO card);

    @CloudFunction("020102")
    String removeBankCard(BankCardDTO card);

    @CloudFunction("020103")
    String modifyBankCard(BankCardDTO card);

    @CloudFunction("020104")
    List<BankCardDTO> getBankCard(String CerNumber);

    @CloudFunction("020105")
    BankCardDTO getOneBankCard(String CardNumber, String CerNumber);

}
