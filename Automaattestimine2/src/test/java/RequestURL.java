import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RequestURL {
    @Test
    public void testHttpConnectionToApi() {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getResponseCode(), 200);
        } catch (IOException ioq) {
            fail("HTTP connection threw error:" + ioq.getLocalizedMessage());
        }

    }

}
