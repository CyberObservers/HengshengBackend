package tongji.product.server.mapper;


import org.apache.ibatis.annotations.*;
import tongji.product.api.pojo.BankCardDTO;

import java.util.List;

@Mapper
public interface BankCardMapper {
    @Insert("insert into bank_card(bank_name, card_number, cer_number, balance) values(#{card.bankName},#{card.cardNumber},#{card.cerNumber},#{card.balance})")
    int createBankCard(@Param("card") BankCardDTO card);

    @Select("SELECT bank_name as bankName,card_number as cardNumber,cer_number as cerNumber,balance from bank_card where cer_number=#{CerNumber}")
    List<BankCardDTO> getCard(@Param("CerNumber")String CerNumber);

    @Update("UPDATE bank_card set bank_name=#{card.bankName}, balance=#{card.balance} WHERE card_number=#{card.cardNumber}")
    int updateCard(@Param("card") BankCardDTO card);

    @Delete("DELETE from bank_card where card_number=#{CardNumber} and cer_number=#{CerNumber}")
    int deleteCard(@Param("CardNumber")String CardNumber, @Param("CerNumber")String CerNumber);

    @Select("SELECT bank_name as bankName,card_number as cardNumber,cer_number as cerNumber,balance from bank_card where card_number=#{CardNumber} and cer_number=#{CerNumber}")
    BankCardDTO getOneCard(@Param("CardNumber")String CardNumber, @Param("CerNumber")String CerNumber);
}
