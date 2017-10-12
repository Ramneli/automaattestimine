import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.CurrentWeatherReportOfDate;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;

public class testGetForecastForDate {
    @Test
    public void testGetForecastForDate() throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherReport = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        assertTrue(currentWeatherReport.getWeatherOfDate(LocalDate.of(2017, 10, 12)) instanceof CurrentWeatherReportOfDate);
    }
}
