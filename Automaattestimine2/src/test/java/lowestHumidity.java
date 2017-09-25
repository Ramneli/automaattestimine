public class lowestHumidity {
    @Test
    public void testLowestHumidityPercentage() {
        assertFalse(getHumidityPercentage() < 0);
    }
}
