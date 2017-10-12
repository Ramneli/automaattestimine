import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class test3DayMaxTempHigherMin {
    @Test
    public void test3DayMaxTempHigherMin() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        ThreeDayWeatherReport currentWeatherReport = WeatherForecast.makeThreeDayWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.get3DayAverageMax() > currentWeatherReport.get3DayAverageMin());
    }
}
