package weatherforecast;

import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

public class WeatherForecast {

    public double getCurrentTemperature(WeatherRequest request) {
        return request.getJsonData().get().getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
    }

    public static CurrentWeatherReport makeCurrentWeatherReport(WeatherRequest weatherRequestData) {
        return new CurrentWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                , weatherRequestData.getFormat(), weatherRequestData.getJsonData().get());
    }

    public static ThreeDayWeatherReport makeThreeDayWeatherReport(WeatherRequest weatherRequestData) {
        return new ThreeDayWeatherReport(weatherRequestData.getCityName(), weatherRequestData.getCountryCode()
                , weatherRequestData.getFormat(), weatherRequestData.getJsonData().get());
    }
}
