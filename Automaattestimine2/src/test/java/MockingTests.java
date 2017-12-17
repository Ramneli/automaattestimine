import filereader.Reader;
import filewriter.Writer;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.WeatherRequest;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class MockingTests {
    private WeatherRequest weatherRequest = new WeatherRequest();
    private ArrayList<ArrayList> cities;
    private ArrayList<String> oneCity;

    @Before
    public void setup() {
        ArrayList<ArrayList> cities = new ArrayList<ArrayList>();
        ArrayList<String> oneCity = new ArrayList<String>();
        oneCity.addAll(Arrays.asList(new String[]{"Paris", "FR", "metric"}));
        cities.add(oneCity);
    }
    @After
    public void tearDown() {
        cities = null;
        oneCity = null;
    }
    @Test
    public void testMock_FileReadAndWrite_Dependency() throws MalformedURLException {
        Writer writer = mock(Writer.class);
        Reader reader = mock(Reader.class);

        when(reader.read(Mockito.anyString())).thenReturn(cities);
        when(reader.makeNestedData((String[]) Mockito.any())).thenReturn(cities);
        when(reader.readWeatherData(Mockito.anyString(), Mockito.anyString(), (ArrayList<ArrayList>) Mockito.any()))
                .thenReturn("{}");
        doNothing().when(writer).writeToFile((CurrentWeatherReport) Mockito.any());
        weatherRequest.ofFile("tere.txt", reader, writer);
    }

    @Test
    public void testMock_API_Dependency() throws MalformedURLException {
        ArrayList<ArrayList> cities = new ArrayList<ArrayList>();
        ArrayList<String> oneCity = new ArrayList<String>();
        oneCity.addAll(Arrays.asList(new String[]{"Paris", "FR", "metric"}));
        cities.add(oneCity);

        CurrentWeatherRequest currentWeatherRequest = new CurrentWeatherRequest();
        WeatherForecast weatherForecast = new WeatherForecast();
        Reader reader = mock(Reader.class);

        when(reader.readWeatherData(Mockito.anyString(), Mockito.anyString(), (ArrayList<ArrayList>) Mockito.any())).thenReturn("{\"main\":{\"temp\":999.99}}");
        when(reader.read(Mockito.anyString())).thenReturn(cities);
        when(reader.makeNestedData((String[]) Mockito.any())).thenReturn(cities);

        weatherRequest = currentWeatherRequest.of("Paris", "FR", "metric", reader);
        CurrentWeatherReport weatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
        TestCase.assertEquals(999.99, weatherReport.getCurrentTemperature());
    }
}
