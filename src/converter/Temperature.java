/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author j393554
 */
public class Temperature {
    /**
     * Convert (c)elsius (To) (F)ahrenheit
     *
     * @param celsius The value to convert to Fahrenheit
     * @return The conversion result
     */
    public static double cToF( double celsius ) {
        return ( celsius * ( (double) 9 / (double) 5 ) + 32 );
    }

    /**
     * Convert (F)ahrenheit (To) (Celsius)
     *
     * @param fahrenheit The value to convert to Celsius
     * @return The conversion result
     */
    public static double fToC( double fahrenheit ) {
        return ( ( fahrenheit - 32 ) * ( (double) 5 / (double) 9 ) );
    }

    /**
     * Returns relevant units used for this measurement.
     *
     * @return
     */
    public static String[] Units() {
        String[] units;

        units = new String[2];

        units[0] = "Celsius";
        units[1] = "Fahrenheit";

        return units;
    }
}