package servlets.Waiter;

import helpers.Waiter.DeleteOrderHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/DeleteOrder", urlPatterns = "/Waiter/DeleteOrder")
public class DeleteOrder extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new DeleteOrderHelper(request, response)
                .deleteOrder();
    }
}
