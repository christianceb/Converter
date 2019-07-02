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
public class ConversionCoordinator {


    /**
     * Converts a number from its unit of measurement to its relevant target unit
     *
     * @param measurement There are 3 units supported: Length, Weight, Temperature.
     * @param convert The value to be converted from
     * @param fromUnit The unit "convert" (value to convert) is using
     * @param toUnit The target unit
     * @return Double
     */
    public static Double convert( String measurement, Double convert, String fromUnit, String toUnit ) {
        Double converted = 0.0;

        switch ( measurement ) {
            case "Length" :
                if ( fromUnit == "Inches" && toUnit == "Centimeter" ) {
                    converted = Length.inToCm( convert );
                }
                else if ( fromUnit == "Centimeter" && toUnit == "Inches" ) {
                    converted = Length.cmToIn( convert );
                }
                break;
            case "Weight" :
                if ( fromUnit == "Pound" && toUnit == "Kilogram" ) {
                    converted = Weight.lbToKg( convert );
                }
                else if ( fromUnit == "Kilogram" && toUnit == "Pound" ) {
                    converted = Weight.kgToLb( convert );
                }
                break;
            case "Temperature" :
                if ( fromUnit == "Celsius" && toUnit == "Fahrenheit" ) {
                    converted = Temperature.cToF( convert );
                }
                else if ( fromUnit == "Fahrenheit" && toUnit == "Celsius" ) {
                    converted = Temperature.fToC( convert );
                }
                break;
            case "Time" :
                if ( fromUnit == "Second" && toUnit == "Day" ) {
                    converted = Time.sToD( convert );
                }
                else if ( fromUnit == "Day" && toUnit == "Second" ) {
                    converted = Time.dToS( convert );
                }
                break;
            case "Currency" :
                if ( fromUnit == "USD" && toUnit == "AUD" ) {
                    converted = Currency.usdToAud( convert );
                }
                else if ( fromUnit == "AUD" && toUnit == "USD" ) {
                    converted = Currency.audToUsd( convert );
                }
                break;
        }

        return converted;
    }
}