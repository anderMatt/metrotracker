package metrotracker.api;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import metrotracker.api.WmataApi;
import metrotracker.WmataApiException;

@WebServlet(urlPatterns = {"/api/bus/positions"})  //{routeId}
public class BusPositionApi extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String routeId = req.getParameter("routeId");
        //TODO: handle no param
        String busPositionsJson;

        try {
            busPositionsJson = WmataApi.getBusPositions(routeId);
            res.setContentType("application/json");
            PrintWriter writer = res.getWriter();
            writer.write(busPositionsJson);

        } catch(WmataApiException e) {
            //TODO: handle exception, send err response
            res.sendError(500, "Sorry, there was a problem!");
        }
    }
}
