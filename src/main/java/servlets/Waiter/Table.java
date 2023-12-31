package servlets.Waiter;

import helpers.Waiter.TableHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/Table", urlPatterns = "/Waiter/Table")
public class Table extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new TableHelper(request, response)
                .displayTable();
    }
}
