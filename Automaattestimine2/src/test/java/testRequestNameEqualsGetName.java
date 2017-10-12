import junit.framework.TestCase;
import org.junit.Test;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class testRequestNameEqualsGetName {

    @Test
    public void testRequestedNameEqualsApiName() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");
            TestCase.assertEquals(request.getCityName(),
                    request.getJsonData().get().getJSONObject("city").getString("name"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
