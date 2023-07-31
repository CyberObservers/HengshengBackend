package tongji.product.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tongji.product.api.pojo.RiskTraceDTO;

import java.util.Date;

@Mapper
public interface RiskTraceMapper {
    @Insert("insert into risk_trace(fund_number, cer_number, red_date) values(#{riskTrace.fundNumber}, #{riskTrace.cerNumber}, #{riskTrace.redDate})")
    int createRiskTrace(@Param("riskTrace")RiskTraceDTO riskTrace);

    @Select("select fund_number as fundNumber, cer_number as cerNumber, red_date as redDate from risk_trace where cer_number=#{cerNumber} and red_date=#{date}")
    RiskTraceDTO getRiskTrace(@Param("cerNumber") String cerNumber, @Param("date") Date date);

}
