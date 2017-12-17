package weatherreport;

import org.json.JSONObject;


public class ThreeDayWeatherReport {

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
        if (isValidDay(day) && validDataListSize()) {
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

    public double getLowestTemperatureOfDay(int day) throws IllegalArgumentException {
        double minTemperature = 999;
        if (isValidDay(day) && validDataListSize()) {
            for (int i = 0; i < (day * 8) - 1; i++) {
                double currentMin = jsonData.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp_min");
                if (currentMin < minTemperature) {
                    minTemperature = currentMin;
                }
            }
            return minTemperature;
        }
        throw new IllegalArgumentException();
    }

    public int getDataCount() {
        return (int) jsonData.get("cnt");
    }

    public String getCityName() {
        return jsonData.getJSONObject("city").getString("name");
    }

    public String getCountryCode() {
        return jsonData.getJSONObject("city").getString("country");
    }

    public String getFormat() {
        return format;
    }
    public boolean validDataListSize() {
        return jsonData.getJSONArray("list").length() >= 24;
    }
}
