import org.junit.Ignore;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class testSunset {
    @Ignore
    public void testLatestSunSetEstonia() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
       // assertTrue(currentWeatherReport.getSunsetTime() < 2300
           //     && currentWeatherReport.getSunriseTime() > 1520);
    }
}
