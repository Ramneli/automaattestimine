package weatherrequest;

import filereader.Reader;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class CurrentWeatherRequest extends WeatherRequest {

    public WeatherRequest of(String cityName, String countryCode, String format, Reader reader) throws MalformedURLException {
        WeatherRequest weatherRequest = new WeatherRequest(cityName, countryCode, format);
        String[] data = {weatherRequest.getCityName()+","+weatherRequest.getCountryCode()+","+weatherRequest.getFormat()};
        ArrayList<ArrayList> nestedData =  reader.makeNestedData(data);
        String currentWeather = makeCurrentDataUrl(nestedData);
        String currentJsonData = readWeatherData("Current", currentWeather, nestedData, reader);
        try {
            weatherRequest.setCurrentWeatherData(new JSONObject(currentJsonData));
        } catch (JSONException e) {
            System.out.println("JSON could not be created.");
        }
        return weatherRequest;
    }
}
