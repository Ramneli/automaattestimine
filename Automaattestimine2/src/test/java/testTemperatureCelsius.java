import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class testTemperatureCelsius {
    @Test
    public void testTemperatureCelsiusLimits() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getCurrentTemperature() <= 50
                && currentWeatherReport.getCurrentTemperature() >= -50);
    }
}
