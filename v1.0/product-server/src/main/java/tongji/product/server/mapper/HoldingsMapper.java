package tongji.product.server.mapper;
import org.apache.ibatis.annotations.*;
import tongji.product.api.pojo.HoldingsDTO;
import java.util.List;

@Mapper
public interface HoldingsMapper {

    @Insert("INSERT INTO investor_holdings(fund_number, cer_number, total_share, card_number) VALUES (#{holdings.fundNumber}, #{holdings.cerNumber}, #{holdings.totalShare}, #{holdings.cardNumber})")
    int createHoldings(@Param("holdings") HoldingsDTO holdings);

    @Select("SELECT fund_number AS fundNumber, cer_number AS cerNumber, total_share AS totalShare, card_number AS cardNumber FROM investor_holdings WHERE cer_number = #{cerNumber}")
    List<HoldingsDTO> getHoldings(@Param("cerNumber") String cerNumber);

    @Update("UPDATE investor_holdings SET total_share = #{holdings.totalShare} WHERE fund_number = #{holdings.fundNumber} AND cer_number = #{holdings.cerNumber} AND card_number = #{holdings.cardNumber}")
    int updateHoldings(@Param("holdings") HoldingsDTO holdings);

    @Delete("DELETE FROM investor_holdings WHERE total_share=0")
    int deleteHoldings();

    @Select("SELECT fund_number AS fundNumber, cer_number AS cerNumber, total_share AS totalShare, card_number AS cardNumber FROM investor_holdings WHERE card_number = #{cardNumber} AND cer_number = #{cerNumber}")
    HoldingsDTO getOneHoldings(@Param("cardNumber") String cardNumber, @Param("cerNumber") String cerNumber);

    @Select("SELECT fund_number AS fundNumber, cer_number AS cerNumber, total_share AS totalShare, card_number AS cardNumber FROM investor_holdings " +
            "WHERE card_number = #{cardNumber} " +
            "AND cer_number = #{cerNumber} " +
            "AND fund_number = #{fundNumber}")
    HoldingsDTO getOnlyHoldings(@Param("cardNumber") String cardNumber, @Param("cerNumber") String cerNumber,@Param("fundNumber") String fundNumber);
}
