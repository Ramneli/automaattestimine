public class dayTemperatureHigherThanNight {
    @Test
    public void testDayTempHigherThanNight() {
        assertTrue(get5DayAverageDay() > get5DayAverageNight);
    }
}
