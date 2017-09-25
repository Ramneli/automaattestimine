public class earliestSunrise {
    @Test
    public void testEarliestSunriseEstonia() {
        assertFalse(getCountry() == "EE" && getSunriseTime() < "04:00");
    }
}
