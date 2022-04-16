package servlet.order;

import entity.Order;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class ToPayServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService service = new OrderServiceImpl();
        int[] result = (int[]) req.getSession().getAttribute("result");

        //获取地址id
        int aid = Integer.parseInt(req.getParameter("aid"));
        System.out.println(aid);


        List<Order> orders =new ArrayList<Order>();
        orders = (List<Order>) req.getSession().getAttribute("orders");

        for (int j=0;j<orders.size();j++){
            service.updateOrderIsPay(orders.get(j).getOrder_id(), "1"); // 挨个查询订单id 并改is_ship 为 1
            service.updateOrderAddress(orders.get(j).getOrder_id(),aid);    // 挨个设置 地址 id
        }


        resp.sendRedirect("ok.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
