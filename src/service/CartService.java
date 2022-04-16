package service;

import entity.Cart;

import java.util.List;

public interface CartService {
    int findCartCountByUserId(int user_id);

    List<Cart> findCartListByUserId(int i);

    void addCart(Cart cart);

    void deleteCart(int id);

    Cart findCartCountByCartId(int id);

    int addnumber(int id);

    int minnumber(int id);
}
