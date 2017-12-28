package metrotracker.utli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonResponseReader {

    public static String readJsonFromResponse(InputStream is) throws IOException {
        StringBuilder body = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while( (line = br.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }
}
