package servlet.address;

import entity.Address;
import service.AddressService;
import service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateAddressServlet")
public class updateAddressServlet extends HttpServlet {
    AddressService addressService = new AddressServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address a =new Address();
        int user_id = (int) req.getSession().getAttribute("user_id");
        String id = req.getParameter("aid");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        System.out.println(id);
        a.setUser_id(user_id);
        a.setUser_name(name);
        a.setUser_phone(phone);
        a.setUser_address(address);
        addressService.updateAddress(a);
        req.getRequestDispatcher("/toMyAddressListServlet").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
