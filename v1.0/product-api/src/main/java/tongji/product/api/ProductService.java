package tongji.product.api;
import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import tongji.product.api.pojo.ProductDTO;

import java.util.List;

@CloudService(validationNull = true, validation = true)
public interface ProductService {
    @CloudFunction("0102")
    String createProduct(ProductDTO Product);

    @CloudFunction("0104")
    ProductDTO getProduct(String fundNumber);

//    @CloudFunction("0105")
//    List<ProductDTO> getProductByParams(String )

//<<<<<<< Updated upstream
//    @CloudFunction("333002")
//    String modifyProduct(ProductDTO Product);
//=======
    @CloudFunction("0103")
    String modifyProduct(ProductDTO Product);

    @CloudFunction("0106")
    String deleteProduct(String fundNumber);

    @CloudFunction("0107")
    List<ProductDTO>getAllProduct();


//>>>>>>> Stashed changes
}
