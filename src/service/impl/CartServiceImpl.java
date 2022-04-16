package service.impl;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import entity.Cart;
import service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao dao = new CartDaoImpl();

    @Override
    public int findCartCountByUserId(int user_id) {
        return dao.findCartCountByUserId(user_id);
    }

    @Override
    public List<Cart> findCartListByUserId(int i) {
        return dao.findCartListByUserId(i);
    }

    @Override
    public void addCart(Cart cart) {
        dao.addCart(cart);
    }
    @Override
    public Cart findCartCountByCartId(int id) {
        return dao.findCartCountByCartId(id);
    }
    @Override
    public void deleteCart(int id) {
        dao.deleteCart(id);
    }
    @Override
    public int addnumber(int id) {
        return dao.addnumber(id);
    }
    @Override
    public int minnumber(int id) {
        return dao.minnumber(id);
    }
}
