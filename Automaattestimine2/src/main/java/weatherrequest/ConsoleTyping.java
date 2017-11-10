package weatherrequest;

import java.io.*;

public class ConsoleTyping {
    public static void initiateTyping(WeatherRequest weatherRequest) throws IOException {
        String cityName = "";
        String cityCode = "";
        String code = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        showOptions();
        String option = br.readLine();
        while (notValidOption(option)) {
            System.out.println("Invalid input!");
            showOptions();
            option = br.readLine();
        }
        if (option.equals("1")) {
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
        } else {
            String[] data = readFromFile();
            cityName = data[0];
            cityCode = data[1];
            code = data[2];
            weatherRequest.setCityName(cityName);
            weatherRequest.setCountryCode(cityCode);
            weatherRequest.setFormat(code);
        }
    }

    private static void showOptions() {
        System.out.println("Pick an option for request:");
        System.out.println("1 - Manual data insertion.");
        System.out.println("2 - Read from file.");
    }

    private static boolean notValidUnitSystem(String code) {
        return !code.equalsIgnoreCase("metric") && !code.equalsIgnoreCase("imperial");
    }

    private static boolean notValidCityCode(String cityCode) {
        return cityCode.matches(".*\\d+.*") || cityCode.length() != 2;
    }

    private static boolean notValidCityName(String cityName) {
        return cityName.equals("") || cityName.isEmpty() || cityName.matches(".*\\d+.*");
    }

    private static boolean notValidOption(String option) {
        return !option.equals("1") && !option.equals("2");
    }

    private static String[] readFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("input.txt")));
        return bufferedReader.readLine().split(";");
    }
}
