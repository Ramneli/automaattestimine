import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.CurrentWeatherReportOfDate;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CurrentWeatherTests {
    private WeatherRequest weatherRequest;
    private CurrentWeatherReport currentWeatherReport;
    @Before
    public void setUp() throws MalformedURLException {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
    }
    @After
    public void tearDown() {
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
            WeatherRequest metric = new WeatherRequest("Tallinn", "EE", "metric");
            WeatherRequest imperial = new WeatherRequest("Tallinn", "EE", "Imperial");

            CurrentWeatherReport metricReport = WeatherForecast.makeCurrentWeatherReport(metric);
            CurrentWeatherReport imperialReport = WeatherForecast.makeCurrentWeatherReport(imperial);

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
    public void testGetForecastForDate() {
        assertTrue(currentWeatherReport.getWeatherOfDate(LocalDate.of(2017, 10, 12))
                instanceof CurrentWeatherReportOfDate);
    }
    @Test
    public void testVisibilityValues() throws MalformedURLException {
        assertTrue(currentWeatherReport.getVisibility() >= 0 && currentWeatherReport.getVisibility() <= 25000);
    }
}
