package weatherrequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class ThreeDayWeatherRequest extends WeatherRequest {

    public static final String THREE_DAY = "3 day";

    public static WeatherRequest of(String cityName, String countryCode, String format) throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest(cityName, countryCode, format);
        String threeDayWeather = makeThreeDayDataUrl(weatherRequest.getCityName(), weatherRequest.getCountryCode(),
                weatherRequest.getFormat());
        String threeDayJsonData = readWeatherData(THREE_DAY, threeDayWeather, weatherRequest.getCityName(), weatherRequest.getCountryCode(),
                weatherRequest.getFormat());
        try {
            weatherRequest.threeDayWeatherRawData = new JSONObject(threeDayJsonData);
        } catch (JSONException e) {
            System.out.println("JSON could not have been created.");
        }
        return weatherRequest;
    }
}
