package helpers.Customer;

import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomerLoginHelper <T extends CustomerLoginHelper> extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String tableIdIdentifier = "tableID";
    private long tableID = -1;

    public CustomerLoginHelper(@NonNull Connection connection,
                               @NonNull HttpServletRequest request,
                               @NonNull HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;
        log.info("Customer Login Helper Object is Generated.");
    }

    public void goToMenu() {
        try {
            log.info("Redirecting to Menu Servlet.");
            response.sendRedirect("/RestaurantServer/Menu");
        }catch(IOException ex) {redirectToErrorPage(request, response, ex); }
    }

    public T validateTableID() {
        try {
            log.info("Validating Table ID.");
            long tableID = Long.parseLong(request.getParameter(tableIdIdentifier));
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.SELECT_TABLE_ID());

            List<String> existingTableID = new ArrayList<String>();
            while(resultSet.next())
                existingTableID.add(resultSet.getString(1));

            if(existingTableID.contains(Long.toString(tableID))) {
                this.tableID = tableID;
                log.info("Table ID verified.");
            } else {
                log.info("Table ID not found in the server.");
                redirectToErrorPage(request, response, "Table ID not found.");
            }
        }catch(Exception ex) {
            log.debug("Exception occurred while validating Table ID.");
            redirectToErrorPage(request, response, ex);
        }
        return (T) this;
    }

    public T addCookie() {
        deleteExistingCookie();
        if(tableID != -1) {
            Cookie cookie = new Cookie(tableIdIdentifier, Long.toString(tableID));
            response.addCookie(cookie);
            log.info("Cookie Saved successfully.\nCookie Details : " + cookie);
        } else {
            log.info("Cookie not added to browser due to Table ID not verified.");
            redirectToErrorPage(request, response, "Table ID not found in the system.");
        }
        return (T) this;
    }

    public T updateCustomerEntry() {
        if(tableID != -1) {
            try {
                connection.createStatement()
                        .executeUpdate(SQLQueries.UPDATE_CUSTOMER_STATUS(Long.toString(tableID)));
                log.info("Updated customer login.\nCustomer Status changed to active.");
            }catch(SQLException ex) {
                log.info("Failed to update Customer Status in the DB.\n" + ex);
                redirectToErrorPage(request, response, ex);
            }
        }else {
            log.info("Not able to change Status in the DB as TableID is not verified.");
            redirectToErrorPage(request, response, "Table ID not verified.");
        }
        return (T) this;
    }

    private void deleteExistingCookie() {
        log.info("Deleting cooking implementation not done yet.");
    }
}
