import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class testPercipitation {
    @Ignore
    public void testLowestPercipitation() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        //TestCase.assertTrue(currentWeatherReport.getPercipitation() >= 0
                //&& currentWeatherReport.getPercipitation() <= 10);
    }
}
