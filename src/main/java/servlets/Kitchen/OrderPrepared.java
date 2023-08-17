package servlets.Kitchen;

import helpers.OrderPreparedHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderPrepared")
public class OrderPrepared extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new OrderPreparedHelper(request, response)
                .orderPrepared();
    }
}
