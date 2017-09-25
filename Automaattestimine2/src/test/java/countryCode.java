public class countryCode {
    @Test
    public void testLegalCountryCode() {
        assertTrue(getCountryCode().length == 2 && getCountryCode() instanceof String);
    }
}
