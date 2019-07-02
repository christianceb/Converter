package converter;

public class Currency {
    /**
     * Exchange rates as of April 29, 2019.
     *
     * Source: Morningstar via Google Finance: https://www.google.com/intl/en/googlefinance/disclaimer/
     */
    private static final double AUD_IN_USD = 0.71;
    private static final double USD_IN_AUD = 1.42;

    /**
     * Converts from AUD to USD
     *
     * @param aud The value to convert to USD
     * @return The conversion result
     */
    public static double audToUsd( double aud ) {
        return aud * AUD_IN_USD;
    }

    /**
     * Converts from USD to AUD
     *
     * @param usd The value to convert to AUD
     * @return The conversion result
     */
    public static double usdToAud( double usd ) {
        return usd * USD_IN_AUD;
    }

    /**
     * Returns currencies used by this conversion.
     *
     * @return
     */
    public static String[] Units() {
        String[] units;

        units = new String[2];
        units[0] = "AUD";
        units[1] = "USD";

        return units;
    }
}
