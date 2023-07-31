package tongji.product.server.mapper;


import org.apache.ibatis.annotations.*;
import tongji.product.api.pojo.ProductDTO;

import java.util.List;

@Mapper
public interface ProductMapper {


    @Insert("insert into fund_product(fund_number, fund_name, fund_type,fund_risk) VALUES (#{fund.fundNumber},#{fund.fundName},#{fund.fundType},#{fund.fundRisk})")
    int createProduct(@Param("fund")ProductDTO fund);

    @Select("SELECT fund_number as fundNumber,fund_name as fundName,fund_type as fundType,fund_risk as fundRisk from fund_product where fund_number=#{number} ")
    ProductDTO getProduct(@Param("number")String number);

    @Select("<script>" +
            "SELECT fund_number AS fundNumber, fund_name AS fundName, fund_type AS fundType, fund_risk AS fundRisk FROM fund_product " +
            "<where>" +
            "<if test='fundNumber != null'>AND fund_number = #{fundNumber}</if>" +
            "<if test='fundName != null'>AND fund_name = #{fundName}</if>" +
            "<if test='fundType != null'>AND fund_type = #{fundType}</if>" +
            "<if test='fundRisk != null'>AND fund_risk = #{fundRisk}</if>" +
            "</where>" +
            "</script>")
    List<ProductDTO> getProductByParams(@Param("fundNumber") String fundNumber, @Param("fundName") String fundName, @Param("fundType") String fundType, @Param("fundRisk") Integer fundRisk);

    @Update("UPDATE fund_product SET fund_name=#{product.fundName}, fund_risk=#{product.fundRisk}, fund_type=#{product.fundType} WHERE fund_number=#{product.fundNumber}")
    int updateProduct(@Param("product") ProductDTO product);

    @Delete("DELETE FROM fund_product WHERE fund_number=#{fundNumber}")
    int deleteProduct(@Param("fundNumber") String fundNumber);

    @Select("SELECT fund_number AS fundNumber,fund_name AS fundName,fund_type as fundType, fund_risk as fundRisk from fund_product ")
    List<ProductDTO> getAllProduct();

}
