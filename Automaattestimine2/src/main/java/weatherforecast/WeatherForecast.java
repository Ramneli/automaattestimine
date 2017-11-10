package weatherforecast;

import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

import java.util.NoSuchElementException;

public class WeatherForecast {

    public static CurrentWeatherReport makeCurrentWeatherReport(WeatherRequest weatherRequestData) {
        try {
            return new CurrentWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                    , weatherRequestData.getFormat(), weatherRequestData.getCurrentWeatherJsonData().get());
        } catch (NoSuchElementException e) {
            System.out.println("Weather request was denied for " + weatherRequestData.getCityName()
            + ", " + weatherRequestData.getCountryCode() + ", " + weatherRequestData.getFormat());
        }
        throw new NoSuchElementException();
    }

    public static ThreeDayWeatherReport makeThreeDayWeatherReport(WeatherRequest weatherRequestData) {
        try {
            return new ThreeDayWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                    , weatherRequestData.getFormat(), weatherRequestData.getThreeDayWeatherJsonData().get());
        } catch (NoSuchElementException e) {
            System.out.println("Weather request was denied for " + weatherRequestData.getCityName()
                    + ", " + weatherRequestData.getCountryCode() + ", " + weatherRequestData.getFormat());
        }
        throw new NoSuchElementException("JSON missing");
    }
}
