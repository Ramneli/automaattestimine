package weatherrequest;

import java.io.*;

public class ConsoleTyping {

    void initiateTyping(WeatherRequest weatherRequest) throws IOException {
        String cityName = "";
        String cityCode = "";
        String code = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (weatherRequest.getCityName().equals("")) {
            System.out.println("Enter city name: ");
            cityName = br.readLine();
            while (notValidCityName(cityName)) {
                System.out.println("Invalid input!" + "\n");
                System.out.println("Enter city name: ");
                cityName = br.readLine();
            }
            weatherRequest.setCityName(cityName);
        }
        if (weatherRequest.getCountryCode().equals("")) {
            System.out.println("Enter country code: ");
            cityCode = br.readLine();
            while (notValidCityCode(cityCode)) {
                System.out.println("Invalid input!" + "\n");
                System.out.println("Enter country code: ");
                cityCode = br.readLine();
            }
            weatherRequest.setCountryCode(cityCode);
        }
        if (weatherRequest.getFormat().equals("")) {
            System.out.println("Enter unit system: ");
            code = br.readLine();
            while (notValidUnitSystem(code)) {
                System.out.println("Valid options: metric - imperial.");
                System.out.println("Enter unit system: ");
                code = br.readLine();
            }
            weatherRequest.setFormat(code);
        }
    }

    public static boolean notValidUnitSystem(String code) {
        return !code.equalsIgnoreCase("metric") && !code.equalsIgnoreCase("imperial");
    }

    public static boolean notValidCityCode(String cityCode) {
        return cityCode.matches(".*\\d+.*") || cityCode.length() != 2;
    }

    public static boolean notValidCityName(String cityName) {
        return cityName.equals("") || cityName.isEmpty() || cityName.matches(".*\\d+.*");
    }
}
