public class forecastUnit {
    @Test
    public void testForecastUnit() {
        assertTrue(getForecastUnit().equals("3h") || getForecastUnit().equals("5d"));
    }
}
