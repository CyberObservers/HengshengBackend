package tongji.product.server.mapper;

import org.apache.ibatis.annotations.*;
import tongji.product.api.pojo.InvesterDTO;

import java.util.List;

@Mapper
public interface InvesterMapper {
    @Insert("insert into invester(user_type, user_name, cer_type, cer_number,risk_grade) VALUES(#{invester.userType},#{invester.userName},#{invester.cerType},#{invester.cerNumber},#{invester.riskGrade})")
    int createInvester(@Param("invester")InvesterDTO invester);

    @Select("SELECT user_type as userType,user_name as userName,cer_type as cerType,cer_number as cerNumber,risk_grade as riskGrade from invester where cer_number=#{number} ")
    InvesterDTO getInvester(@Param("number")String number);

    @Select("SELECT user_type as userType,user_name as userName,cer_type as cerType,cer_number as cerNumber,risk_grade as riskGrade from invester where cer_number=#{number} and available=1")
    InvesterDTO getAvailableInvester(@Param("number")String number);

    @Select("select user_type as userType,user_name as userName,cer_type as cerType,cer_number as cerNumber,risk_grade as riskGrade from invester where available=1")
    List<InvesterDTO> getAllInvester();

    @Select("select user_type as userType,user_name as userName,cer_type as cerType,cer_number as cerNumber,risk_grade as riskGrade from invester " +
            "where user_name like #{name} and available=1")
    List<InvesterDTO> searchInvesterViaName(@Param("name") String keyWord);

    @Select("select user_type as userType,user_name as userName,cer_type as cerType,cer_number as cerNumber,risk_grade as riskGrade from invester " +
            "where cer_number like #{number} and available=1")
    List<InvesterDTO> searchInvesterViaNumber(@Param("number") String keyWord);

    @Delete("update invester set available = 0 where cer_number = #{cerNumber}")
    int deleteInvester(@Param("cerNumber")String cerNumber);

    @Update("UPDATE invester SET user_type =#{invester.userType},user_name = #{invester.userName},cer_type = #{invester.cerType}," +
            "cer_number = #{invester.cerNumber}, risk_grade = #{invester.riskGrade} WHERE cer_number =#{invester.cerNumber}")
    int updateInvester(@Param("invester")InvesterDTO invester);

}
