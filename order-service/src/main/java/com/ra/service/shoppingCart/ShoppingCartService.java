package com.ra.service.shoppingCart;

import com.ra.model.dto.request.ShoppingCartRequest;
import com.ra.model.dto.response.ShoppingCartResponse;
import com.ra.model.entity.Shopping_Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    Page<ShoppingCartResponse> getAll(Pageable pageable);

    Shopping_Cart save(Shopping_Cart shoppingCart);

    void deleteOneProduct(Long id);

    void deleteAllProduct();

    Shopping_Cart findById(Integer Id);

    Shopping_Cart convertShoppingCartRequestToShoppingCart(ShoppingCartRequest shoppingCartRequest);

    ShoppingCartResponse convertShoppingCartToShoppingCartResponse(Shopping_Cart shoppingCart);

    void checkOut();
}
