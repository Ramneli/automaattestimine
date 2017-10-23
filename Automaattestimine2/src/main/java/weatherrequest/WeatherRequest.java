package weatherrequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

public class WeatherRequest {
    private String cityName;
    private String countryCode;
    private String format;
    private JSONObject currentWeatherRawData;
    private JSONObject threeDayWeatherRawData;

    public WeatherRequest(String cityName, String cityCode, String format) throws MalformedURLException {
        this.cityName = cityName;
        this.countryCode = cityCode;
        this.format = format;
        String currentWeather = makeCurrentDataUrl(cityName, cityCode, format);
        String threeDayWeather = makeThreeDayDataUrl(cityName, cityCode, format);
        String currentJsonData = readWeatherData(currentWeather);
        String threeDayJsonData = readWeatherData(threeDayWeather);
        this.currentWeatherRawData = new JSONObject(currentJsonData);
        this.threeDayWeatherRawData = new JSONObject(threeDayJsonData);

    }

    private String makeCurrentDataUrl(String cityName, String cityCode, String format) {
        String defaultStart = "http://api.openweathermap.org/data/2.5/weather?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + "{" + cityName + "}" + "," + "{" + cityCode + "}"
                + "&units=" + format + API_KEY;
    }
    private String makeThreeDayDataUrl(String cityName, String cityCode, String format) {
        String defaultStart = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + "{" + cityName + "}" + "," + "{" + cityCode + "}"
                + "&units=" + format + API_KEY;
    }

    public Optional<JSONObject> getCurrentWeatherJsonData() {
        return Optional.of(this.currentWeatherRawData);
    }
    public Optional<JSONObject> getThreeDayWeatherJsonData() {
        return Optional.of(this.threeDayWeatherRawData);
    }

    private String readWeatherData(String dataUrl) throws MalformedURLException {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(dataUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public String getCityName() {
        return cityName;
    }

    public String getFormat() {
        return format;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
