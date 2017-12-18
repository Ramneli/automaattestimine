import filereader.Reader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.ThreeDayWeatherRequest;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ThreeDayWeatherTests {
    private WeatherRequest weatherRequest;
    private ThreeDayWeatherReport threeDayWeatherReport;
    private ThreeDayWeatherRequest threeDayWeatherRequest = new ThreeDayWeatherRequest();
    private WeatherForecast weatherForecast = new WeatherForecast();
    private Reader reader;
    @Before
    public void setUp() throws MalformedURLException {
        reader = new Reader();
        weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
        threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
    }
    @After
    public void tearDown() {
        reader = null;
        weatherRequest = null;
        threeDayWeatherReport = null;
    }
    @Test
    public void testThreeDaysValidForeCastDataAmount() throws MalformedURLException {
        assertTrue(threeDayWeatherReport.getDataCount() >= 24);
    }
    @Test
    public void test3DayMaxTempHigherMin() throws Exception {
        assertTrue(threeDayWeatherReport.getHighestTemperatureOfDay(2)
                > threeDayWeatherReport.getLowestTemperatureOfDay(2));
    }

}
