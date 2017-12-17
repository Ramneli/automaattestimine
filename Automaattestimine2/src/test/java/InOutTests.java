import filereader.Reader;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.ThreeDayWeatherRequest;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class InOutTests {
    private WeatherRequest weatherRequest;
    private CurrentWeatherRequest currentWeatherRequest;
    private WeatherForecast weatherForecast;
    private ThreeDayWeatherRequest threeDayWeatherRequest;
    private Reader reader;

    @Before
    public void setUp() {
        reader = new Reader();
        threeDayWeatherRequest = new ThreeDayWeatherRequest();
        currentWeatherRequest = new CurrentWeatherRequest();
        weatherForecast = new WeatherForecast();
    }
    @After
    public void tearDown() {
        currentWeatherRequest = null;
        threeDayWeatherRequest = null;
        weatherForecast = null;
        reader = null;
    }

    @Test
    public void testCurrentWeatherRequestCityNameEqualsApiName() throws MalformedURLException {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCityName(), currentWeatherReport.getCityName());
    }
    @Test
    public void testCurrentWeatherRequestCityCountryCodeEqualsApiCountryCode() throws MalformedURLException {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCountryCode(), currentWeatherReport.getCountryCode());
    }

    @Test
    public void testThreeDayWeatherRequestCityNameEqualsApiName() throws MalformedURLException {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCityName(), threeDayWeatherReport.getCityName());
    }
    @Test
    public void testThreeDayRequestCityCountryCodeEqualsApiCountryCode() throws MalformedURLException {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCountryCode(), threeDayWeatherReport.getCountryCode());
    }
    @Test
    public void testCurrentWeatherMetricFormat() throws MalformedURLException {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertTrue(currentWeatherReport.getCurrentTemperature() < 60
                    && currentWeatherReport.getCurrentTemperature() > -60);
    }

    @Test
    public void testCurrentWeatherImperialFormat() throws MalformedURLException {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "imperial", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertTrue(currentWeatherReport.getCurrentTemperature() < 140
                    && currentWeatherReport.getCurrentTemperature() > -76);
    }

    @Test
    public void testThreeDayWeatherMetricFormat() throws Exception {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertTrue(threeDayWeatherReport.getThreeDaysAverageTemperature() < 60
                    && threeDayWeatherReport.getThreeDaysAverageTemperature() > -60);

    }

    @Test
    public void testThreeDayWeatherImperialFormat() throws Exception {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "imperial", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertTrue(threeDayWeatherReport.getThreeDaysAverageTemperature() < 140
                    && threeDayWeatherReport.getThreeDaysAverageTemperature() > -76);

    }
}
