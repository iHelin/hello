package me.ianhe.my.prc.service2;

import me.ianhe.my.prc.service1.IProductService;
import me.ianhe.my.prc.service1.Product;

/**
 * @author iHelin
 * @since 2018/4/12 09:55
 */
public class ProductService implements IProductService {
    @Override
    public Product queryById(long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("water");
        product.setPrice(1.0);
        return product;
    }
}