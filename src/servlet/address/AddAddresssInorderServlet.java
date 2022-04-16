package servlet.address;

import entity.Address;
import entity.Order;
import service.AddressService;
import service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addAddresssInorderServlet")
public class AddAddresssInorderServlet extends HttpServlet {
    private AddressService addressService =new AddressServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address add=new Address();
        int user_id = (int) req.getSession().getAttribute("user_id");
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        add.setUser_name(username);
        add.setUser_id(user_id);
        add.setUser_phone(phone);
        add.setUser_address(address);
        addressService.addAddress(add);

        List<Order> orders = new ArrayList<Order>();
        orders = (List<Order>) req.getSession().getAttribute("orders");
        System.out.println(orders);
        req.setAttribute("order", orders);
        AddressService service = new AddressServiceImpl();
        List<Address> addresses = service.findAddressByUserId(user_id);
        req.setAttribute("address",addresses);
        req.getRequestDispatcher("order2.jsp").forward(req, resp);
    }

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
