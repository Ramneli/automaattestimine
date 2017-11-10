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
    private String cityName = "";
    private String countryCode = "";
    private String format = "";
    JSONObject currentWeatherRawData;
    JSONObject threeDayWeatherRawData;

    public WeatherRequest() { }

    public WeatherRequest(String cityName, String cityCode, String format) throws MalformedURLException {
        this.cityName = cityName;
        this.countryCode = cityCode;
        this.format = format;
        checkForMissingValues(cityName, cityCode, format);
    }

    public void checkForMissingValues(String cityName, String cityCode, String format) {
        if (cityName.equals("") || cityCode.equals("") || format.equals("")) {
            try {
                ConsoleTyping.initiateTyping(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String makeCurrentDataUrl(String cityName, String cityCode, String format) {
        String defaultStart = "http://api.openweathermap.org/data/2.5/weather?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + cityName + "," + cityCode + "&units=" + format + API_KEY;
    }
    public static String makeThreeDayDataUrl(String cityName, String cityCode, String format) {
        String defaultStart = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + cityName + "," + cityCode + "&units=" + format + API_KEY;
    }

    public Optional<JSONObject> getCurrentWeatherJsonData() {
        return Optional.ofNullable(this.currentWeatherRawData);
    }
    public Optional<JSONObject> getThreeDayWeatherJsonData() {
        return Optional.ofNullable(this.threeDayWeatherRawData);
    }

    public static String readWeatherData(String mode, String dataUrl, String cityName, String countryCode, String format) throws MalformedURLException {
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
            System.out.println(mode + " weather data could not be read for " + cityName + ", " + countryCode + ", "
            + format);
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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
