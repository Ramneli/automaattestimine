import junit.framework.TestCase;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class testHumidity {
    @Test
    public void testLowestHumidityPercentage() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        TestCase.assertTrue(currentWeatherReport.getHumidity() >= 0
                && currentWeatherReport.getHumidity() >= 100);
    }
}
