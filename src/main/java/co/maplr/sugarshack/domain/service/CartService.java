package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.domain.entity.CartLineEntity;
import co.maplr.sugarshack.domain.entity.ProductEntity;
import co.maplr.sugarshack.domain.repository.CartRepository;
import co.maplr.sugarshack.exception.CartLineNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {
    CartRepository cartRepository;
    ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public List<CartLineEntity> findCarts() {
        return cartRepository.findAll();
    }

    public CartLineEntity saveCartLine(String productId) {

        ProductEntity product = productService.getProductById(productId);
        return
                cartRepository.save(
                        new CartLineEntity(productId,
                                product.getName(),
                                product.getImage(),
                                product.getPrice(),
                                0));
    }

    public void delete(String productId) {
        cartRepository.deleteById(productId);
    }

    public void update(String productId, int qty) {
        CartLineEntity cartLine = cartRepository.findById(productId)
                .orElseThrow(() -> new CartLineNotFoundException(productId));

        cartLine.setQty(qty);
        cartRepository.save(cartLine);
    }
}
