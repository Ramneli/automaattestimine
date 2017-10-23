import org.junit.Ignore;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class testSunrise {
    @Ignore
    public void testEarliestSunriseEstonia() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        //assertTrue(currentWeatherReport.getSunriseTime() > 400 && currentWeatherReport.getSunriseTime() < 915);
    }
}
