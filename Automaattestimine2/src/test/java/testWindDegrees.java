import junit.framework.TestCase;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class testWindDegrees {
    @Test
    public void testMinimumWindDegrees() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        TestCase.assertTrue(currentWeatherReport.getWindDegrees() >= 0
                && currentWeatherReport.getWindDegrees() <= 360);
    }
}
