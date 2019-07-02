package converter;

public class Time {
    private static final double S_IN_D = 86400;
    private static final double D_IN_S = 0.000012;

    /**
     * Converts from Days to Seconds
     *
     * @param days The value to convert to Seconds
     * @return The conversion result.
     */
    public static double dToS( double days ) {
        return days * S_IN_D;
    }
    /**
     * Converts from Seconds to Days
     *
     * @param seconds The value to convert to Inches
     * @return The conversion result.
     */
    public static double sToD( double seconds ) {
        return seconds * D_IN_S;
    }

    /**
     * Returns units used by this conversion.
     *
     * @return
     */
    public static String[] Units() {
        String[] units;

        units = new String[2];

        units[0] = "Day";
        units[1] = "Second";

        return units;
    }
}
