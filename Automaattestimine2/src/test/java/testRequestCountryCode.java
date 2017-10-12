import junit.framework.TestCase;
import org.junit.Test;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class testRequestCountryCode {
    @Test
    public void testRequestedCountryCodeEqualsApiCountryCode() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");
            TestCase.assertEquals(request.getCountryCode(),
                    request.getJsonData().get().getJSONObject("city").getString("country"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
