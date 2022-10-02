package co.maplr.sugarshack.domain.service;

import co.maplr.sugarshack.api.dto.CartLineDto;
import co.maplr.sugarshack.api.dto.MapleSyrupDto;
import co.maplr.sugarshack.domain.entity.CartLineEntity;
import co.maplr.sugarshack.domain.repository.CartRepository;
import co.maplr.sugarshack.exception.CartLineNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {
    CartRepository cartRepository;
    ProductService productService;
    ModelMapper mapper;

    public CartService(CartRepository cartRepository, ProductService productService, ModelMapper mapper) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.mapper = mapper;
    }

    public List<CartLineDto> findCarts() {
        return cartRepository.findAll().stream()
                .map(cartLineEntity -> mapper.map(cartLineEntity, CartLineDto.class))
                .collect(Collectors.toList());
    }

    public CartLineDto save(String productId) {

        MapleSyrupDto product = productService.getProductById(productId);
        return
                mapper.map(cartRepository.save(
                        new CartLineEntity(productId,
                                product.getName(),
                                product.getImage(),
                                product.getPrice(),
                                0)), CartLineDto.class);
    }

    public void delete(String productId) {
        cartRepository.deleteById(productId);
    }

    public void update(String productId, int qty) {
        CartLineEntity cartLine = cartRepository.findById(productId)
                .orElseThrow(() -> new CartLineNotFoundException(productId));
        ;
        cartLine.setQty(qty);
        cartRepository.save(cartLine);
    }
}
