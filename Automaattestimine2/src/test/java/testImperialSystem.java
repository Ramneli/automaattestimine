import junit.framework.TestCase;
import org.junit.Test;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class testImperialSystem {
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
}
