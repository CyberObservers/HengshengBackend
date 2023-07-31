package tongji.product.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tongji.product.api.pojo.CardStatementDTO;

import java.util.Date;
import java.util.List;

@Mapper
public interface CardStatementMapper {
    @Insert("insert into card_statement(card_number,state_date,state_amount,fund_number,sta_balance) " +
            "values(#{card.cardNumber},#{card.stateDate},#{card.stateAmount},#{card.fundNumber},#{card.staBalance})")
    int addCardStatement(@Param("card") CardStatementDTO cardStatement);

    @Select("select card_number as cardNumber, state_date as stateDate, state_amount as stateAmount, fund_number as fundNumber, sta_balance as staBalance " +
            "from card_statement where card_number=#{card} and fund_number=#{fund}")
    List<CardStatementDTO> getCardStatementViaFund(@Param("card") String cardNumber, @Param("fund") String fundNumber);

    @Select("select card_number as cardNumber, state_date as stateDate, state_amount as stateAmount, fund_number as fundNumber, sta_balance as staBalance " +
            "from card_statement where card_number=#{card} and state_date=#{date}")
    List<CardStatementDTO> getCardStatementViaDate(@Param("card") String cardNumber, @Param("date") Date date);

    @Select("select card_number as cardNumber, state_date as stateDate, state_amount as stateAmount, fund_number as fundNumber, sta_balance as staBalance " +
            "from card_statement where card_number=#{card} and fund_number=#{fund} and state_date=#{date}")
    CardStatementDTO getCardStatement(@Param("card") String cardNumber, @Param("fund") String fundNumber, @Param("date") Date date);
}
