package servlet.order;


import entity.Address;
import entity.Cart;
import entity.Order;
import service.AddressService;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.impl.AddressServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/toOrderServletFromCart")
public class ToOrderServletFromCart extends HttpServlet {
private ProductService productService = new ProductServiceImpl();
private CartService cartService = new CartServiceImpl();
private OrderService orderService =new OrderServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int user_id = (int) req.getSession().getAttribute("user_id");   // 获取user id
        String arr = req.getParameter("eids");
        String [] temp = arr.split(",");
        int[] result = new int[temp.length];
        req.getSession().setAttribute("result", result);
        for (int j=0;j<temp.length;j++){
            result[j] = Integer.parseInt(temp[j]);
        }
        int totalPrice = 0;
        List<Order> orders =new ArrayList<Order>();
        for (int i=0;i<temp.length;i++){
            Cart cartCountByCartId = cartService.findCartCountByCartId(result[i]);
            Order order= new Order();
            order.setOrder_id(System.currentTimeMillis() +""+ user_id);
            order.setUser_id(user_id);
            order.setOrder_price(cartCountByCartId.getProduct_price()*cartCountByCartId.getProduct_quantity());
            order.setOrder_time(new Date());
            order.setIs_pay("0");
            order.setIs_ship("0");
            order.setIs_receipt("0");
            order.setAddress_id(0);
            order.setProduct_quantity(cartCountByCartId.getProduct_quantity());
            order.setProduct_id(cartCountByCartId.getProduct_id());
            order.setProduct_name(cartCountByCartId.getProduct_name());
            order.setProduct_photo(cartCountByCartId.getProduct_photo());
            order.setProduct_price(cartCountByCartId.getProduct_price());
            orders.add(order);
            totalPrice +=cartCountByCartId.getProduct_price()*cartCountByCartId.getProduct_quantity();
        }
        req.setAttribute("order", orders);
        req.getSession().setAttribute("orders", orders);
        for (int i=0;i<orders.size();i++){
            orderService.addOrder(orders.get(i));
        }
        AddressService service = new AddressServiceImpl();
        List<Address> addresses = service.findAddressByUserId(user_id);
        req.setAttribute("address",addresses);

        for (int k=0;k<result.length;k++){
            cartService.deleteCart(result[k]);
        }

        req.setAttribute("totalPrice",totalPrice);
        req.getSession().setAttribute("totalPrice", totalPrice);
        req.getRequestDispatcher("order2.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
