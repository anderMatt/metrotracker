package metrotracker.api;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import metrotracker.api.WmataApi;
import metrotracker.WmataApiException;

@WebServlet(urlPatterns = {"/api/bus/positions"})  //{routeId}
public class BusPositionApi extends HttpServlet{
    private static final Logger log = Logger.getLogger(BusPositionApi.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String routeId = req.getParameter("routeId");
        //TODO: handle no param
        String busPositionsJson = "";

        try {
            busPositionsJson = WmataApi.getBusPositions(routeId);
            busPositionsJson = formatClientResponse(busPositionsJson);
        } catch(WmataApiException e) {
             busPositionsJson = formatClientErrResponse("Sorry, unable to retrieve bus positions");
        }
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        writer.write(busPositionsJson);
    }

    private String formatClientResponse(String responseData) {
        return "{data: " + responseData + "}";
    }

    private String formatClientErrResponse(String errMsg) {
        return "{error: " + errMsg + "}";
    }

}
