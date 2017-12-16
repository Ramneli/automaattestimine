import filereader.Reader;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CurrentWeatherTests {
    private WeatherRequest weatherRequest;
    private CurrentWeatherReport currentWeatherReport;
    private CurrentWeatherRequest currentWeatherRequest = new CurrentWeatherRequest();
    private WeatherForecast weatherForecast;
    private Reader reader;

    @Before
    public void setUp() throws MalformedURLException {
        weatherForecast = new WeatherForecast();
        reader = new Reader();
        weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
        currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
    }
    @After
    public void tearDown() {
        reader = null;
        weatherRequest = null;
        currentWeatherReport = null;
    }

    @Test
    public void testMinimumWindSpeed() {
        TestCase.assertTrue(currentWeatherReport.getWindSpeed() <= 100 && currentWeatherReport.getWindSpeed() >= 0);
    }

    @Test
    public void testMinimumWindDegrees() {
        TestCase.assertTrue(currentWeatherReport.getWindDegrees() >= 0
                && currentWeatherReport.getWindDegrees() <= 360);
    }

    @Test
    public void testTemperatureFahrenheitLimits() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "Imperial");
        assertTrue(currentWeatherReport.getCurrentTemperature() >= -58
                && currentWeatherReport.getCurrentTemperature() <= 122);
    }
    @Test
    public void testTemperatureCelsiusLimits() {
        Assert.assertTrue(currentWeatherReport.getCurrentTemperature() <= 50
                && currentWeatherReport.getCurrentTemperature() >= -50);
    }
    @Test
    public void testlowestPressure() {
        TestCase.assertTrue(currentWeatherReport.getPressure() >= 870 && currentWeatherReport.getPressure() <= 1083);
    }
    @Test
    public void testImperialValueBiggerThanMetric() {
        try {
            Reader reader1 = new Reader();
            WeatherRequest metric = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
            WeatherRequest imperial = currentWeatherRequest.of("Tallinn", "EE", "imperial", reader1);
            CurrentWeatherReport metricReport = weatherForecast.makeCurrentWeatherReport(metric);
            CurrentWeatherReport imperialReport = weatherForecast.makeCurrentWeatherReport(imperial);
            TestCase.assertTrue(imperialReport.getCurrentTemperature() > metricReport.getCurrentTemperature());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLowestHumidityPercentage()  {
        TestCase.assertTrue(currentWeatherReport.getHumidity() >= 0
                && currentWeatherReport.getHumidity() <= 100);
    }
    @Test
    public void testCloudinessValue() {
        assertTrue(currentWeatherReport.getCloudiness() >= 0 && currentWeatherReport.getCloudiness() <= 100);
    }

    @Test
    public void testVisibilityValues() throws MalformedURLException {
        assertTrue(currentWeatherReport.getVisibility() >= 0 && currentWeatherReport.getVisibility() <= 25000);
    }
    @Test
    public void testEarliestSunriseEstonia() throws MalformedURLException {
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
        CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getSunriseTime().getHours() >= 4
                && currentWeatherReport.getSunriseTime().getHours() <= 9);
    }
    @Test
    public void testLatestSunSetEstonia() throws MalformedURLException {
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
        CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getSunsetTime().getHours() <= 23
                && currentWeatherReport.getSunsetTime().getHours() >= 15);
    }
}
