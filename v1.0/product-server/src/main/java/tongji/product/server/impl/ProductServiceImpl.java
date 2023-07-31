package tongji.product.server.impl;

import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.ProductService;
import tongji.product.api.pojo.ProductDTO;
import tongji.product.server.mapper.ProductMapper;

import java.util.List;

@CloudComponent
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public String createProduct(ProductDTO Product) {
        ProductDTO existProduct = productMapper.getProduct(Product.getFundNumber());
        if(null == existProduct){
            productMapper.createProduct(Product);
            return Product.getFundNumber();
        }
        throw new IllegalArgumentException("已存在相同的证件号的投资者");
    }

    public ProductDTO getProduct(String fundNumber) {
        Assert.hasLength(fundNumber, "缺少查询的产品代码");
        return productMapper.getProduct(fundNumber);
    }

    public String modifyProduct(ProductDTO Product) {
        ProductDTO existProduct = productMapper.getProduct(Product.getFundNumber());
        if(null == existProduct){
            //productMapper.createProduct(Product);
            return "不存在该产品";
        }
        productMapper.updateProduct(Product);
        return Product.getFundNumber();
    }

    public String deleteProduct(String fundNumber){
        ProductDTO existProduct = productMapper.getProduct(fundNumber);
        if(null == existProduct){
            //productMapper.createProduct(Product);
            return "不存在该产品";
        }
        productMapper.deleteProduct(fundNumber);
        return "成功删除产品";
    }

    public List<ProductDTO> getAllProduct() {
        return productMapper.getAllProduct();
    }
}
