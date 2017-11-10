package main;

import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.ThreeDayWeatherRequest;
import weatherrequest.WeatherRequest;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        WeatherRequest weatherRequest = ThreeDayWeatherRequest.of("Tallinn", "EE", "metric");
        ThreeDayWeatherReport threeDayWeatherReport = WeatherForecast.makeThreeDayWeatherReport(weatherRequest);
        System.out.println(threeDayWeatherReport.getHighestTemperatureOfDay(3));
        //currentWeatherReport.writeToFile();
    }
}
