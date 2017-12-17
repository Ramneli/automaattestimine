package filewriter;

import filereader.Reader;
import weatherforecast.WeatherForecast;
import weatherreport.CurrentWeatherReport;
import weatherreport.ThreeDayWeatherReport;
import weatherrequest.CurrentWeatherRequest;
import weatherrequest.ThreeDayWeatherRequest;
import weatherrequest.WeatherRequest;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    public void writeToFile(CurrentWeatherReport currentWeatherReport)  {
        try {
            String cityName = currentWeatherReport.getCityName();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(cityName+".txt")));
            bufferedWriter.write("City name: " + cityName + "\n"
            + "City coordinates: " + currentWeatherReport.getGeoGraphicalCoordinates() + "\n"
            + "Current temperature: " + currentWeatherReport.getCurrentTemperature() + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error in writing file!" + "\n" + e);
        }
    }

    public void writeToFile(ThreeDayWeatherReport threeDayWeatherReport) {
        try {
            String cityName = threeDayWeatherReport.getCityName();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(cityName+".txt"), true));
            bufferedWriter.write("Day 1 min, max: " + threeDayWeatherReport.getLowestTemperatureOfDay(1)
            + "\t" + threeDayWeatherReport.getHighestTemperatureOfDay(1) + "\n"
            + "Day 2 min, max: " + threeDayWeatherReport.getLowestTemperatureOfDay(2)
                    + "\t" + threeDayWeatherReport.getHighestTemperatureOfDay(2) + "\n"
            + "Day 3 min, max: " + threeDayWeatherReport.getLowestTemperatureOfDay(3)
                    + "\t" + threeDayWeatherReport.getHighestTemperatureOfDay(3));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error in writing file!" + "\n" + e);
        }
    }

    public void readAndWrite(Reader reader, String fileURL) {
        ArrayList<ArrayList> allCities = reader.read(fileURL);
        CurrentWeatherRequest cWeatherRequest = new CurrentWeatherRequest();
        ThreeDayWeatherRequest tDayWeatherRequest = new ThreeDayWeatherRequest();
        WeatherForecast weatherForecast = new WeatherForecast();
        List<CurrentWeatherReport> currentWeatherReports = new ArrayList<>();
        List<ThreeDayWeatherReport> threeDayWeatherReports = new ArrayList<>();

        for (int j = 0; j < allCities.size(); j++) {
            String city = (String) allCities.get(j).get(0);
            String code = (String) allCities.get(j).get(1);
            String format = (String) allCities.get(j).get(2);
            try {
                WeatherRequest currentWeatherRequest = cWeatherRequest.of(city, code, format, reader);
                WeatherRequest threeDayWeatherRequest = tDayWeatherRequest.of(city, code, format, reader);
                currentWeatherReports.add(weatherForecast.makeCurrentWeatherReport(currentWeatherRequest));
                threeDayWeatherReports.add(weatherForecast.makeThreeDayWeatherReport(threeDayWeatherRequest));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < currentWeatherReports.size(); i++) {
            this.writeToFile(currentWeatherReports.get(i));
            this.writeToFile(threeDayWeatherReports.get(i));
        }
    }
}
