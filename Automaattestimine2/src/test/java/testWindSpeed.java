import junit.framework.TestCase;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class testWindSpeed {
        @Test
        public void testMinimumWindSpeed() throws MalformedURLException {
            WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
            CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertTrue(currentWeatherReport.getWindSpeed() <= 100 && currentWeatherReport.getWindSpeed() >= 0);
        }
    }

