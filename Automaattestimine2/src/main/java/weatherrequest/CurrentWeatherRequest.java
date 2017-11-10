package weatherrequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class CurrentWeatherRequest extends WeatherRequest {

    public static final String CURRENT = "Current";

    public static WeatherRequest of(String cityName, String cityCode, String format) throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest(cityName, cityCode, format);
        String currentWeather = makeCurrentDataUrl(weatherRequest.getCityName(), weatherRequest.getCountryCode(),
                weatherRequest.getFormat());
        String currentJsonData = readWeatherData(CURRENT, currentWeather, weatherRequest.getCityName()
                , weatherRequest.getCountryCode(), weatherRequest.getFormat());
        try {
            weatherRequest.currentWeatherRawData = new JSONObject(currentJsonData);

        } catch (JSONException e) {
            System.out.println("JSON could not be created.");
        }
        return weatherRequest;
    }
}
