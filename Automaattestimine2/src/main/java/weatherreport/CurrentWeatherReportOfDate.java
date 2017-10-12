package weatherreport;

import weatherreport.CurrentWeatherReport;

import java.time.LocalDate;

public class CurrentWeatherReportOfDate extends CurrentWeatherReport {
    public CurrentWeatherReportOfDate(LocalDate date) {
        super(date);
    }
}
