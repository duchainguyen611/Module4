package com.ra.service.shoppingCart;

import com.ra.model.dto.request.ShoppingCartRequest;
import com.ra.model.dto.response.ShoppingCartResponse;
import com.ra.model.entity.ENUM.StatusOrders;
import com.ra.model.entity.Order_Detail;
import com.ra.model.entity.Orders;
import com.ra.model.entity.Shopping_Cart;
import com.ra.repository.ShoppingCartRepository;
import com.ra.service.address.AddressService;
import com.ra.service.orderDetail.OrderDetailService;
import com.ra.service.orders.OrdersService;
import com.ra.service.product.ProductService;
import com.ra.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceIMPL implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailService orderDetailService;

    Logger logger = LoggerFactory.getLogger(ShoppingCartServiceIMPL.class);
    @Override
    public Page<ShoppingCartResponse> getAll(Pageable pageable) {
        Page<Shopping_Cart> shoppingCarts = shoppingCartRepository.findAllByUser(userService.userLogin(), pageable);
        return shoppingCarts.map(this::convertShoppingCartToShoppingCartResponse);
    }

    @Override
    public Shopping_Cart save(Shopping_Cart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteOneProduct(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllProduct() {
        shoppingCartRepository.deleteByUser();
    }

    @Override
    public Shopping_Cart findById(Integer Id) {
        return shoppingCartRepository.findByIdAndUser(Id, );
    }

    public Shopping_Cart convertShoppingCartRequestToShoppingCart(ShoppingCartRequest shoppingCartRequest) {
        Product product = productService.findById(shoppingCartRequest.getProductId());
        if (product == null) {
            throw new RuntimeException();
        }
        return Shopping_Cart.builder().
                orderQuantity(shoppingCartRequest.getOrderQuantity()).
                product(product).
                user(userService.userLogin()).
                build();
    }

    public ShoppingCartResponse convertShoppingCartToShoppingCartResponse(Shopping_Cart shoppingCart) {
        return ShoppingCartResponse.builder()
                .productName(shoppingCart.getProduct().getProductName())
                .unitPrice(shoppingCart.getProduct().getUnitPrice())
                .orderQuantity(shoppingCart.getOrderQuantity())
                .build();
    }

    @Transactional
    @Override
    public void checkOut() {
        if (address==null){
            logger.error("ID Address not found in this User");
            throw new RuntimeException();
        }
        Orders orders = new Orders();
        orders.setSerialNumber(UUID.randomUUID().toString());
        orders.setStatusOrders(StatusOrders.WAITING);
        orders.setNote("");
        orders.setCreatedAt(LocalDate.now());
        orders.setReceiveName(address.getReceiveName());
        orders.setReceiveAddress(address.getFullAddress());
        orders.setReceivePhone(address.getPhone());
        orders.setReceivedAt(LocalDate.now().plusDays(4));
        List<Shopping_Cart> shoppingCarts = shoppingCartRepository.findAllByUser(userService.userLogin());
        double sum = 0;
        for (Shopping_Cart cart: shoppingCarts) {
            sum+=cart.getOrderQuantity()*cart.getProduct().getUnitPrice();
            Order_Detail order_detail = new Order_Detail();
            order_detail.setName(cart.getProduct().getProductName());
            order_detail.setOrder(orders);
            order_detail.setOrderQuantity(cart.getOrderQuantity());
            order_detail.setUnitPrice(cart.getProduct().getUnitPrice());
            order_detail.setProduct(productService.findById(cart.getProduct().getId()));
            orderDetailService.addOrderDetail(order_detail);
        }
        orders.setTotalPrice(sum);
        orders.setUser(userService.userLogin());
        ordersService.addOrders(orders);
        deleteAllProduct();
    }
}
