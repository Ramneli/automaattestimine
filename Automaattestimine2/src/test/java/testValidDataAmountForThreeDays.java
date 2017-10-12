import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class testValidDataAmountForThreeDays {
    @Test
    public void testThreeDaysValidForeCastDataAmount() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        ThreeDayWeatherReport threeDayWeatherReport = WeatherForecast.makeThreeDayWeatherReport(weatherRequest);
        assertEquals(40, threeDayWeatherReport.getDataCount());
    }
}
