package converter;

public class Length {
    private static final double IN_IN_CM = 0.393701;
    private static final double CM_IN_IN = 2.54;

    /**
     * Converts from Inches to Centimeters
     *
     * @param inches The value to convert to centimeters
     * @return The conversion result
     */
    public static double inToCm( double inches ) {
        return inches * CM_IN_IN;
    }

    /**
     * Converts from Centimeters to Inches
     *
     * @param centimeters The value to convert to Inches
     * @return The conversion result.
     */
    public static double cmToIn( double centimeters ) {
        return centimeters * IN_IN_CM;
    }

    /**
     * Returns units used by this conversion.
     *
     * @return
     */
    public static String[] Units() {
        String[] units;

        units = new String[2];
        units[0] = "Inches";
        units[1] = "Centimeter";

        return units;
    }
}