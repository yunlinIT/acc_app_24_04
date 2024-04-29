package com.koreait.exam.acc_app_2024_04.app.cart.service;

import com.koreait.exam.acc_app_2024_04.app.cart.entity.CartItem;
import com.koreait.exam.acc_app_2024_04.app.cart.repository.CartItemRepository;
import com.koreait.exam.acc_app_2024_04.app.member.entity.Member;
import com.koreait.exam.acc_app_2024_04.app.product.entity.Product;
import com.koreait.exam.acc_app_2024_04.app.song.entity.Song;
import com.koreait.exam.acc_app_2024_04.app.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartItem addItem(Member buyer, Product product) {
        CartItem oldCartItem = cartItemRepository.findByBuyerIdAndProductId(buyer.getId(), product.getId()).orElse(null);

        if (oldCartItem != null) {
            return oldCartItem;
        }

        CartItem cartItem = CartItem.builder()
                .buyer(buyer)
                .product(product)
                .build();

        cartItemRepository.save(cartItem);

        return cartItem;
    }

    public boolean removeItem(Member buyer, Product product) {
        CartItem oldCartItem = cartItemRepository.findByBuyerIdAndProductId(buyer.getId(), product.getId()).orElse(null);

        if (oldCartItem != null) {
            cartItemRepository.delete(oldCartItem);
            return true;
        }
        return false;
    }

    public boolean hasItem(Member buyer, Product product) {

        return cartItemRepository.existsByBuyerIdAndProductId(buyer.getId(), product.getId());
    }
}
