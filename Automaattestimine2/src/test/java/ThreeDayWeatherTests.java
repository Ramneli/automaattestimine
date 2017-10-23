import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ThreeDayWeatherTests {
    private WeatherRequest weatherRequest;
    private ThreeDayWeatherReport threeDayWeatherReport;
    @Before
    public void setUp() throws MalformedURLException {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        threeDayWeatherReport = WeatherForecast.makeThreeDayWeatherReport(weatherRequest);
    }
    @After
    public void tearDown() {
        weatherRequest = null;
        threeDayWeatherReport = null;
    }
    @Test
    public void testThreeDaysValidForeCastDataAmount() throws MalformedURLException {
        assertEquals(40, threeDayWeatherReport.getDataCount());
    }
    @Test
    public void test3DayMaxTempHigherMin() throws Exception {
        assertTrue(threeDayWeatherReport.getHighestTemperatureOfDay(2)
                > threeDayWeatherReport.getLowestTemperatureOfDay(2));
    }

}