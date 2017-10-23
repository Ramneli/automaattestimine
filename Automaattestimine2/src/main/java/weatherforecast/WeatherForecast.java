package weatherforecast;

import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

public class WeatherForecast {

    public static CurrentWeatherReport makeCurrentWeatherReport(WeatherRequest weatherRequestData) {
        return new CurrentWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                , weatherRequestData.getFormat(), weatherRequestData.getCurrentWeatherJsonData().get());
    }

    public static ThreeDayWeatherReport makeThreeDayWeatherReport(WeatherRequest weatherRequestData) {
        return new ThreeDayWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                , weatherRequestData.getFormat(), weatherRequestData.getThreeDayWeatherJsonData().get());
    }
}
