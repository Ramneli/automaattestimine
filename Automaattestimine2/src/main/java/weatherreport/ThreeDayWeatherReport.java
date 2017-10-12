package weatherreport;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThreeDayWeatherReport {
    private ArrayList<Double> threeDayTemperaturesAverage = new ArrayList<Double>();
    private ArrayList<ArrayList> threeDayTemperaturesMinMax = new ArrayList<ArrayList>();

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

    List<Double> getThreeDayTemperaturesAverage() {
        for (int i = 0; i < 24; i++) {
            threeDayTemperaturesAverage.add(jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp"));
        }
        return threeDayTemperaturesAverage;
    }

    double getHighestTemperature3Days() {
        double maxTemperature = -999;
        for (Double threeDayTemperature : threeDayTemperaturesAverage) {
            if (threeDayTemperature > maxTemperature) {
                maxTemperature = threeDayTemperature;
            }
        }
        return maxTemperature;
    }

    List<ArrayList> getThreeDayTemperaturesMinMax() {
        for (int i = 0; i < 24; i++) {
            double min = jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp_min");
            double max = jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp_max");
            ArrayList<Double> tempMinMax = new ArrayList<>();
            tempMinMax.add(min);
            tempMinMax.add(max);
            threeDayTemperaturesMinMax.add(tempMinMax);
        }
        return threeDayTemperaturesMinMax;
    }

    public double get3DayAverageMax() {
        return -977;
    }

    public double get3DayAverageMin() {
        return -999;
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
}
