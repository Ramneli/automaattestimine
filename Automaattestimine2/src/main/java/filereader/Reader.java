package filereader;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {

    private ArrayList<ArrayList> allCitiesData = new ArrayList<>();

    public ArrayList<ArrayList> read(String URL) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(URL)));
            String fileLine = bufferedReader.readLine();
            String[] citiesSplit = fileLine.split(";");
            allCitiesData = makeNestedData(citiesSplit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCitiesData;
    }

    public ArrayList<ArrayList> makeNestedData(String[] cities) {
        ArrayList<ArrayList> allCitiesData = new ArrayList<>();
        for (String city : cities) {
            String[] oneCityData = city.split(",");
            ArrayList<String> cityData = new ArrayList<>();
            cityData.addAll(Arrays.asList(oneCityData));
            allCitiesData.add(cityData);
        }
        return allCitiesData;
    }

    public String readWeatherData(String mode, String dataUrl, ArrayList<ArrayList> nestedData) {
        StringBuilder content = new StringBuilder();
        String cityName = (String) nestedData.get(0).get(0);
        String countryCode = (String) nestedData.get(0).get(1);
        String format = (String) nestedData.get(0).get(2);
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
}
