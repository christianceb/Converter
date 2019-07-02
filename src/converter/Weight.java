package converter;

public class Weight {
    private static final double KG_IN_LB = 2.204623;
    private static final double LB_IN_KG = 0.453592;

    /**
     * Converts from Kilograms to Pounds
     *
     * @param kilograms The value to convert to pounds
     * @return The conversion result.
     */
    public static double kgToLb( double kilograms ) {
        return kilograms * KG_IN_LB;
    }

    /**
     * Converts from Pounds to Kilograms
     *
     * @param pounds The value to convert to kilograms
     * @return The conversion result.
     */
    public static double lbToKg( double pounds ) {
        return pounds * LB_IN_KG;
    }

    /**
     * Returns units used by this conversion.
     *
     * @return
     */
    public static String[] Units() {
        String[] units;

        units = new String[2];
        units[0] = "Kilogram";
        units[1] = "Pound";

        return units;
    }
}