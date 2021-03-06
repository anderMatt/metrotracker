package metrotracker.api;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import metrotracker.WmataApiException;
import metrotracker.model.BusPositions;

@WebServlet(urlPatterns = {"/api/bus/positions"})  //{routeId}
public class BusPositionsApi extends HttpServlet{
    private static final Logger log = Logger.getLogger(BusPositionsApi.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String routeId = req.getParameter("routeId");
        //TODO: handle no param
        BusPositions busPositions;
        String clientResponseBody;
        int statusCode = 200;

        try {
            busPositions = WmataApi.getBusPositions(routeId);
            clientResponseBody = busPositions.toJson();
        } catch(WmataApiException e) {
            clientResponseBody = formatClientErrResponse("Sorry, unable to retrieve bus positions");
            statusCode = 403;
        }
        res.setContentType("application/json");
        res.setStatus(statusCode);
        PrintWriter writer = res.getWriter();
        writer.write(clientResponseBody);
    }

    private String formatClientErrResponse(String errMsg) {
        return "{error: " + errMsg + "}";
    }
}
