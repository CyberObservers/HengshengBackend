package tongji.product.api;

import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.CardStatementDTO;

import java.util.Date;
import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface CardStatementService {
    @CloudFunction("99901")
    String createCardStatement(CardStatementDTO cardStatement);

    @CloudFunction("99902")
    List<CardStatementDTO> getCardStatementViaFund(String cardNumber, String fundNumber);

    @CloudFunction("99903")
    List<CardStatementDTO> getCardStatementViaDate(String cardNumber, Date date);

    @CloudFunction("99904")
    CardStatementDTO getCardStatement(String cardNumber, String fundNumber, Date date);

}
