package weatherreport;

import org.json.JSONObject;

import java.time.LocalDate;
;

public class CurrentWeatherReport {
    private String cityName;
    private String countryCode;
    private String format;
    private JSONObject jsonData;

    CurrentWeatherReport(LocalDate date) {

    }

    public CurrentWeatherReport(String cityName, String countryCode, String format, JSONObject object) {
        this.cityName = cityName;
        this.countryCode= countryCode;
        this.format = format;
        this.jsonData = object;
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

    public double getCurrentTemperature() {
        return jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
    }

    String getMessage() {
        return jsonData.get("message").toString();
    }
    public int getCloudiness() {
        return (int) jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("clouds").getDouble("all");
    }
    public CurrentWeatherReportOfDate getWeatherOfDate(LocalDate date) {
        return new CurrentWeatherReportOfDate(date);
    }
    public int getSunriseTime() {
        return -650;
    }
    public int getSunsetTime() {
        return 5555;
    }

    public int getHumidity() {
        return (int) jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("humidity");
    }

    public double getPercipitation() {
        return jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("rain").getDouble("3h");
    }

    public double getPressure() {
        return jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("pressure");
    }
    public double getWindDegrees() {
        return jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getDouble("deg");
    }
    public double getWindSpeed() {
        return jsonData.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getDouble("speed");
    }



}
