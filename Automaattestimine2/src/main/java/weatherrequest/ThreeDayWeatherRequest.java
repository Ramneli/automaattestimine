package weatherrequest;

import filereader.Reader;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class ThreeDayWeatherRequest extends WeatherRequest {

    public WeatherRequest of(String cityName, String countryCode, String format, Reader reader) throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest(cityName, countryCode, format);
        String[] data = {weatherRequest.getCityName()+","+weatherRequest.getCountryCode()+","+weatherRequest.getFormat()};
        ArrayList<ArrayList> nestedData =  reader.makeNestedData(data);
        String threeDayWeather = makeThreeDayDataUrl(nestedData);
        String threeDayJsonData = readWeatherData("3 day", threeDayWeather, nestedData, reader);
        try {
            weatherRequest.setThreeDayWeatherRawData(new JSONObject(threeDayJsonData));
        } catch (JSONException e) {
            System.out.println("JSON could not have been created.");
        }
        return weatherRequest;
    }
}
