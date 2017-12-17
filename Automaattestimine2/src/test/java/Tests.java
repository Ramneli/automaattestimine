import filereader.Reader;
import junit.framework.TestCase;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.WeatherRequest;
import weatherrequest.ConsoleTyping;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Tests {
    private CurrentWeatherRequest currentWeatherRequest;

    @Before
    public void setUp() {
        currentWeatherRequest = new CurrentWeatherRequest();
    }
    @After
    public void tearDown() {
        currentWeatherRequest = null;
    }

    @Test
    public void testMakeCurrentWeatherURL() {
        String expectedResult = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn,EE&units=metric&APPID=f9a9920a6532b6e73fefddf1f100be12";
        ArrayList<ArrayList> cityArray = new ArrayList<ArrayList>(Arrays.asList(new ArrayList<String>
                (Arrays.asList(new String[]{"Tallinn", "EE", "metric"}))));
        WeatherRequest weatherRequest = new WeatherRequest();
        String URL = weatherRequest.makeCurrentDataUrl(cityArray);
        TestCase.assertEquals(expectedResult, URL);
    }

    @Test
    public void testMake3DayWeatherURL() {
        String expectedResult = "http://api.openweathermap.org/data/2.5/forecast?q=Tallinn,EE&units=metric&APPID=f9a9920a6532b6e73fefddf1f100be12";
        ArrayList<ArrayList> cityArray = new ArrayList<ArrayList>(Arrays.asList(new ArrayList<String>
                (Arrays.asList(new String[]{"Tallinn", "EE", "metric"}))));
        WeatherRequest weatherRequest = new WeatherRequest();
        String URL = weatherRequest.makeThreeDayDataUrl(cityArray);
        TestCase.assertEquals(expectedResult, URL);
    }

    @Test
    public void testSetCountryCode() throws MalformedURLException {
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric"
                , new Reader());
        TestCase.assertEquals("EE", weatherRequest.getCountryCode());
    }

    @Test
    public void testSetFormat() throws MalformedURLException {
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric"
                , new Reader());
        TestCase.assertEquals("metric", weatherRequest.getFormat());
    }
    @Test
    public void testSetCityName() throws MalformedURLException {
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric"
                , new Reader());
        TestCase.assertEquals("Tallinn", weatherRequest.getCityName());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testLowestTemperatureInvalidDay() throws IllegalArgumentException {
        ThreeDayWeatherReport threeDayWeatherReport = new ThreeDayWeatherReport("", "", "", null);
        threeDayWeatherReport.getLowestTemperatureOfDay(4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHighestTemperatureInvalidDay() throws IllegalArgumentException {
        ThreeDayWeatherReport threeDayWeatherReport = new ThreeDayWeatherReport("", "", "", null);
        threeDayWeatherReport.getLowestTemperatureOfDay(4);
    }

    @Test
    public void makeNestedData() {
        Reader reader = new Reader();
        ArrayList<ArrayList> expectedResult = new ArrayList<ArrayList>(Arrays.asList(new ArrayList<String>
                (Arrays.asList(new String[]{"Tallinn", "EE", "metric"}))));
        ArrayList<ArrayList> nestedData = reader.makeNestedData(new String[]{"Tallinn,EE,metric"});

        TestCase.assertEquals(expectedResult, nestedData);
    }

    @Test
    public void testManualSetCityName() {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setCityName("Moscow");
        TestCase.assertEquals("Moscow", weatherRequest.getCityName());
    }

    @Test
    public void testManualSetCountryCode() {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setCountryCode("FI");
        TestCase.assertEquals("FI", weatherRequest.getCountryCode());
    }

    @Test
    public void testManualSetFormat() {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setFormat("metric");
        TestCase.assertEquals("metric", weatherRequest.getFormat());
    }

    @Test
    public void testConsoleTypingFormatCondition() {
        TestCase.assertFalse(ConsoleTyping.notValidUnitSystem("imperial"));
        TestCase.assertFalse(ConsoleTyping.notValidUnitSystem("metric"));
        TestCase.assertTrue(ConsoleTyping.notValidUnitSystem("tere"));
    }

    @Test
    public void testConsoleTypingCityCodeCondition() {
        TestCase.assertFalse(ConsoleTyping.notValidCityCode("EE"));
        TestCase.assertFalse(ConsoleTyping.notValidCityCode("FI"));
        TestCase.assertTrue(ConsoleTyping.notValidCityCode("d2"));
        TestCase.assertTrue(ConsoleTyping.notValidCityCode(""));
        TestCase.assertTrue(ConsoleTyping.notValidCityCode("abc"));
    }

    @Test
    public void testConsoleTypingCityNameCondition() {
        TestCase.assertFalse(ConsoleTyping.notValidCityName("Tallinn"));
        TestCase.assertTrue(ConsoleTyping.notValidCityName("M0sc0w"));
        TestCase.assertTrue(ConsoleTyping.notValidCityName(""));
    }

    @Test (expected = NoSuchElementException.class)
    public void testCurrentReportNoJson() throws NoSuchElementException {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setCityName("Tallinn");
        weatherRequest.setCountryCode("EE");
        weatherRequest.setFormat("metric");
        WeatherForecast weatherForecast = new WeatherForecast();
        CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
    }

    @Test (expected = NoSuchElementException.class)
    public void test3DayReportNoJson() throws NoSuchElementException {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setCityName("Tallinn");
        weatherRequest.setCountryCode("EE");
        weatherRequest.setFormat("metric");
        WeatherForecast weatherForecast = new WeatherForecast();
        ThreeDayWeatherReport currentWeatherReport = weatherForecast.makeThreeDayWeatherReport(weatherRequest);
    }
}
