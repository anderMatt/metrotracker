package metrotracker.api;

import metrotracker.WmataApiException;
import metrotracker.model.BusRoutes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/api/bus/routes"})
public class BusRoutesApi extends HttpServlet{
    private static final Logger log = Logger.getLogger(BusRoutesApi.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BusRoutes routes = null;
        String clientResponseBody = null;
        int statusCode = 200;

        try {
            routes = WmataApi.getBusRoutes();
            clientResponseBody = routes.toJson();
        } catch(WmataApiException e) {
            clientResponseBody = formatClientErrResponse("Sorry, unable to retrieve bus routes.");
            statusCode = 403;
        }
        res.setContentType("application/json");
        res.setStatus(statusCode);
        PrintWriter writer = res.getWriter();
        writer.write(clientResponseBody);
    }

    //TODO: put in util
    private String formatClientErrResponse(String errMsg) {
        return "{error: " + errMsg + "}";
    }
}
