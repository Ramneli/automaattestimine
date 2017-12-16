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

public class SystemTests {
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
    public void testCurrentWeatherRequestCityNameEqualsApiName() {
        try {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCityName(), currentWeatherReport.getCityName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCurrentWeatherRequestCityCountryCodeEqualsApiCountryCode() {
        try {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCountryCode(), currentWeatherReport.getCountryCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThreeDayWeatherRequestCityNameEqualsApiName() {
        try {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCityName(), threeDayWeatherReport.getCityName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThreeDayRequestCityCountryCodeEqualsApiCountryCode() {
        try {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertEquals(weatherRequest.getCountryCode(), threeDayWeatherReport.getCountryCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCurrentWeatherMetricFormat() {
        try {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertTrue(currentWeatherReport.getCurrentTemperature() < 60
                    && currentWeatherReport.getCurrentTemperature() > -60);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCurrentWeatherImperialFormat() {
        try {
            weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "imperial", reader);
            CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
            TestCase.assertTrue(currentWeatherReport.getCurrentTemperature() < 140
                    && currentWeatherReport.getCurrentTemperature() > -76);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThreeDayWeatherMetricFormat() throws Exception {
        try {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "metric", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertTrue(threeDayWeatherReport.getThreeDaysAverageTemperature() < 60
                    && threeDayWeatherReport.getThreeDaysAverageTemperature() > -60);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThreeDayWeatherImperialFormat() throws Exception {
        try {
            weatherRequest = threeDayWeatherRequest.of("Tallinn", "EE", "imperial", reader);
            ThreeDayWeatherReport threeDayWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
            TestCase.assertTrue(threeDayWeatherReport.getThreeDaysAverageTemperature() < 140
                    && threeDayWeatherReport.getThreeDaysAverageTemperature() > -76);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
