import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.WeatherRequest;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;

import static junit.framework.TestCase.assertTrue;

public class CurrentWeatherMocking {
    private WeatherRequest weatherRequest;
    @Before
    public void setUp() throws MalformedURLException {
        weatherRequest = CurrentWeatherRequest.of("Tallinn", "EE", "metric");
    }
    @After
    public void tearDown() {
        weatherRequest = null;
    }
    @Ignore
    public void testMaxTemperatureLimit() throws MalformedURLException {
        CurrentWeatherRequest currentWeatherRequest = mock(CurrentWeatherRequest.class);
        Mockito.doNothing().when(currentWeatherRequest).checkForMissingValues(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        currentWeatherRequest.checkForMissingValues("", "","");
        currentWeatherRequest.makeCurrentDataUrl("", "", "");
        currentWeatherRequest.readWeatherData("", "", "", "", "");

        InOrder inOrder = inOrder(currentWeatherRequest);
        inOrder.verify(currentWeatherRequest).checkForMissingValues("", "", "");
        inOrder.verify(currentWeatherRequest).makeCurrentDataUrl("", "", "");
        inOrder.verify(currentWeatherRequest).readWeatherData("", "", "", "", "");
    }
}
