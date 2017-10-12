import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class testCloudinessValue {
    @Test
    public void testCloudinessValue() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getCloudiness() >= 0 && currentWeatherReport.getCloudiness() <= 100);
    }
}
