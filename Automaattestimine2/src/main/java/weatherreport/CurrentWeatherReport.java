package weatherreport;

import org.json.JSONObject;
import weatherforecast.WeatherForecast;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;
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
        return jsonData.getJSONObject("main").getDouble("temp");
    }

    String getMessage() {
        return jsonData.getJSONObject("sys").get("message").toString();
    }
    public int getCloudiness() {
        return (int) jsonData.getJSONObject("clouds").getDouble("all");
    }
    public CurrentWeatherReportOfDate getWeatherOfDate(LocalDate date) {
        return new CurrentWeatherReportOfDate(date);
    }
    public Date getSunriseTime() {
        long unixSeconds = jsonData.getJSONObject("sys").getLong("sunrise");
        return convertUnixToNormal(unixSeconds);

    }
    private Date convertUnixToNormal(long unixTime) {
        return Date.from(Instant.ofEpochSecond(unixTime));
    }

    public Date getSunsetTime() {
        long unixSeconds = jsonData.getJSONObject("sys").getLong("sunset");
        return convertUnixToNormal(unixSeconds);
    }

    public int getHumidity() {
        return (int) jsonData.getJSONObject("main").getDouble("humidity");
    }

    public double getPressure() {
        return jsonData.getJSONObject("main").getDouble("pressure");
    }
    public double getWindDegrees() {
        return jsonData.getJSONObject("wind").getDouble("deg");
    }
    public double getWindSpeed() {
        return jsonData.getJSONObject("wind").getDouble("speed");
    }
    public String getGeoGraphicalCoordinates() {
        return "Lon: " + jsonData.getJSONObject("coord").getDouble("lon")
                + "\n" + "Lat: " + jsonData.getJSONObject("coord").getDouble("lat");
    }
    public double getLatitude() {
        return jsonData.getJSONObject("coord").getDouble("lat");
    }
    public double getLongitude() {
        return jsonData.getJSONObject("coord").getDouble("lon");
    }
    public int getVisibility() {
        return jsonData.getInt("visibility");
    }
    public Date getTimeOfDataCalculation() {
        int unixTime = jsonData.getInt("dt");
        return convertUnixToNormal(unixTime);
    }
}
