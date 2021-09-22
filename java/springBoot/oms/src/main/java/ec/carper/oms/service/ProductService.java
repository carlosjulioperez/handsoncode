package ec.carper.oms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ec.carper.oms.data.model.Product;
import ec.carper.oms.data.payloads.request.ProductRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;

/**
 * @Component annotation is a shorthand for the @Bean annotation that register this
 * as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface ProductService {
   MessageResponse createProduct(ProductRequest productRequest);
   Optional <Product> updateProduct(UUID productId, ProductRequest productRequest);
   void deleteProduct (UUID productId);
   Product getAsSinglProduct(UUID productId);
   List <Product> getAllProduct();
}
