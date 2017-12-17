package weatherrequest;

import filereader.Reader;
import filewriter.Writer;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

public class WeatherRequest {
    private String cityName = "";
    private String countryCode = "";
    private String format = "";
    private Reader reader;
    private Writer writer;
    private JSONObject currentWeatherRawData;
    private JSONObject threeDayWeatherRawData;
    private ConsoleTyping consoleTyping = new ConsoleTyping();

    public WeatherRequest() {}

    public void ofFile(String fileURL, Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        writer.readAndWrite(reader, fileURL);
    }

    public WeatherRequest(String cityName, String cityCode, String format) throws MalformedURLException {
        this.cityName = cityName;
        this.countryCode = cityCode;
        this.format = format;
        checkForMissingValues(cityName, cityCode, format);
    }

    private void checkForMissingValues(String cityName, String cityCode, String format) {
        if (cityName.equals("") || cityCode.equals("") || format.equals("")) {
            try {
                consoleTyping.initiateTyping(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String makeCurrentDataUrl(ArrayList<ArrayList> nestedData) {
        String cityName = (String) nestedData.get(0).get(0);
        String countryCode = (String) nestedData.get(0).get(1);
        String units = (String) nestedData.get(0).get(2);
        String defaultStart = "http://api.openweathermap.org/data/2.5/weather?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + cityName + "," + countryCode + "&units=" + units + API_KEY;
    }
    public String makeThreeDayDataUrl(ArrayList<ArrayList> nestedData) {
        String cityName = (String) nestedData.get(0).get(0);
        String countryCode = (String) nestedData.get(0).get(1);
        String units = (String) nestedData.get(0).get(2);
        String defaultStart = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String API_KEY = "&APPID=f9a9920a6532b6e73fefddf1f100be12";
        return defaultStart + cityName + "," + countryCode + "&units=" + units + API_KEY;
    }

    public Optional<JSONObject> getCurrentWeatherJsonData() {
        return Optional.ofNullable(this.currentWeatherRawData);
    }
    public Optional<JSONObject> getThreeDayWeatherJsonData() {
        return Optional.ofNullable(this.threeDayWeatherRawData);
    }

    String readWeatherData(String mode, String dataUrl, ArrayList<ArrayList> nestedData, Reader reader) throws MalformedURLException {
        return reader.readWeatherData(mode, dataUrl, nestedData);
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

    void setCurrentWeatherData(JSONObject currentWeatherData) {
        this.currentWeatherRawData = currentWeatherData;
    }

    void setThreeDayWeatherRawData(JSONObject threeDayWeatherRawData) {
        this.threeDayWeatherRawData = threeDayWeatherRawData;
    }
}
