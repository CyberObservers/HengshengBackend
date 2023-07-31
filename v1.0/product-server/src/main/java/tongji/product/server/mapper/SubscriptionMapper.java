package tongji.product.server.mapper;
import org.apache.ibatis.annotations.*;
import java.util.List;
import tongji.product.api.pojo.SubscriptionDTO;
@Mapper
public interface SubscriptionMapper {
    @Insert("INSERT INTO subscription(sub_state, fund_number, cer_number, sub_amount, sub_date, sub_share, sub_card_number) VALUES (#{subscription.subState}, #{subscription.fundNumber}, #{subscription.cerNumber}, #{subscription.subAmount}, #{subscription.subDate}, #{subscription.subShare}, #{subscription.subCardNumber})")
    int createSubscription(@Param("subscription") SubscriptionDTO subscription);

    @Select("SELECT sub_state AS subState, fund_number AS fundNumber, cer_number AS cerNumber, sub_amount AS subAmount, sub_date AS subDate, sub_share AS subShare, sub_card_number AS subCardNumber FROM subscription WHERE cer_number = #{cerNumber}")
    List<SubscriptionDTO> getSubscriptions(@Param("cerNumber") String cerNumber);

    @Update("UPDATE subscription SET sub_state = #{subscription.subState},  sub_share = #{subscription.subShare} " +
            "WHERE fund_number = #{subscription.fundNumber} AND" +
            " cer_number = #{subscription.cerNumber} AND " +
            "sub_date = #{subscription.subDate} AND " +
            "sub_card_number = #{subscription.subCardNumber}")
    int updateSubscription(@Param("subscription") SubscriptionDTO subscription);

    @Delete("DELETE FROM subscription WHERE sub_card_number = #{subCardNumber} AND cer_number = #{cerNumber}")
    int deleteSubscription(@Param("subCardNumber") String subCardNumber, @Param("cerNumber") String cerNumber);

    @Select("SELECT sub_state AS subState, fund_number AS fundNumber, cer_number AS cerNumber, sub_amount AS subAmount, sub_date AS subDate, sub_share AS subShare, sub_card_number AS subCardNumber FROM subscription WHERE sub_card_number = #{subCardNumber} AND cer_number = #{cerNumber}")
    SubscriptionDTO getOneSubscription(@Param("subCardNumber") String subCardNumber, @Param("cerNumber") String cerNumber);

    @Select("SELECT sub_state AS subState, fund_number AS fundNumber, cer_number AS cerNumber, sub_amount AS subAmount, sub_date AS subDate, sub_share AS subShare, sub_card_number AS subCardNumber FROM subscription WHERE sub_state <> '已上账'")
    List<SubscriptionDTO> getUnsettledSubscriptions();
}
