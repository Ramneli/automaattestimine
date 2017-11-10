package weatherreport;

import filewriter.Writer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeDayWeatherReport {
    private ArrayList<Double> threeDayTemperaturesAverage = new ArrayList<>();
    private ArrayList<ArrayList> threeDayTemperaturesMinMax = new ArrayList<>();

    private Writer writer = new Writer();
    private String cityName;
    private String countryCode;
    private String format;
    private JSONObject jsonData;

    public ThreeDayWeatherReport(String cityName, String countryCode, String format, JSONObject object) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.format = format;
        this.jsonData = object;
    }

    public double getThreeDaysAverageTemperature() throws Exception {
        if (validDataListSize()) {
            double temperature = 0;
            for (int i = 0; i < 24; i++) {
                temperature += jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp");
            }
            return temperature / 24;
        }
        throw new Exception();
    }

    public double getHighestTemperatureOfDay(int day) throws IllegalArgumentException {
        double maxTemperature = -999;
        if (validDataListSize() && isValidDay(day)) {
            for (int i = (day - 1) * 8; i < (day * 8) - 1; i++) {
                double currentMax = jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp_max");
                if (currentMax > maxTemperature) {
                    maxTemperature = currentMax;
                }
            }
            return maxTemperature;
        }
        throw new IllegalArgumentException();
    }

    private boolean isValidDay(int day) {
        return day > 0 && day < 4;
    }

    public double getLowestTemperatureOfDay(int day) throws Exception {
        double minTemperature = 999;
        if (validDataListSize() && isValidDay(day)) {
            for (int i = 0; i < (day * 8) - 1; i++) {
                double currentMin = jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp_min");
                if (currentMin < minTemperature) {
                    minTemperature = currentMin;
                }
            }
            return minTemperature;
        }
        throw new Exception();
    }
    public void writeToFile() {
        this.writer.writeToFile(jsonData);
    }
    public int getDataCount() {
        return (int) jsonData.get("cnt");
    }
    public String getCityName() {
        return cityName;
    }

    String getCountryCode() {
        return countryCode;
    }

    public String getFormat() {
        return format;
    }
    public boolean validDataListSize() {
        return jsonData.getJSONArray("list").length() >= 24;
    }
}
