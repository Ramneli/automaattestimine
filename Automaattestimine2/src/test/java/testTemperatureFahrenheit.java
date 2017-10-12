import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class testTemperatureFahrenheit {
    @Test
    public void testTemperatureFahrenheitLimits() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "Imperial");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getCurrentTemperature() >= -58
                && currentWeatherReport.getCurrentTemperature() <= 122);
    }
}
