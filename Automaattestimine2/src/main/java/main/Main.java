package main;

import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.ThreeDayWeatherRequest;
import weatherrequest.WeatherRequest;
import filereader.Reader;
import filewriter.Writer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        Writer writer = new Writer();
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.ofFile("input.txt", reader, writer);
        /*CurrentWeatherRequest currentWeatherRequest = new CurrentWeatherRequest();
        WeatherForecast weatherForecast = new WeatherForecast();
        WeatherRequest weatherRequest = currentWeatherRequest.of("Tallinn", "EE", "metric", reader);
        CurrentWeatherReport currentWeatherReport = weatherForecast.makeCurrentWeatherReport(weatherRequest);
        System.out.println(currentWeatherReport.getPressure());*/
    }
}
