package main;

import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.WeatherRequest;

import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        CurrentWeatherReport currentWeatherForecast = WeatherForecast.makeCurrentWeatherReport(weatherRequest);
        ThreeDayWeatherReport threeDayWeatherForecast = WeatherForecast.makeThreeDayWeatherReport(weatherRequest);

        System.out.println(currentWeatherForecast.getHumidity());

    }
}
