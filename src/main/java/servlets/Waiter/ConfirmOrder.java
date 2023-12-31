package servlets.Waiter;

import helpers.Waiter.ConfirmOrderHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/ConfirmOrder", urlPatterns = "/Waiter/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new ConfirmOrderHelper(request, response)
                .confirmOrder();
    }
}
