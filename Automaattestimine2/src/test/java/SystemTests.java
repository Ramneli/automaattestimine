import junit.framework.TestCase;
import org.junit.Test;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class SystemTests {
    @Test
    public void testRequestedNameEqualsApiName() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");
            TestCase.assertEquals(request.getCityName(),
                    request.getCurrentWeatherJsonData().get().getString("name"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRequestedCountryCodeEqualsApiCountryCode() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");
            TestCase.assertEquals(request.getCountryCode(),
                    request.getCurrentWeatherJsonData().get().getJSONObject("sys").getString("country"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
