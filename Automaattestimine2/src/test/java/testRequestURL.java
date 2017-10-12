import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class testRequestURL {
    @Test
    public void testHttpConnectionToApi() throws IOException {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=588409&APPID=f9a9920a6532b6e73fefddf1f100be12");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();

            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }

    }

}
