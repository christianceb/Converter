package converter;

public class RecentConversion {
    public String measurement;
    public String fromUnit;
    public String toUnit;
    public String fromValue;
    public String toValue;

    /**
     * Initialize properties of the conversion stored.
     *
     * @param measurement The measurement used in the units. Without this field, we cannot reverse-lookup the measurement
     *                    used from the from/to units efficiently.
     * @param fromUnit The unit used on the value of "From"
     * @param toUnit The unit used on the value of "To"
     * @param fromValue The value of "From"
     * @param toValue The value of "To" which is the result of the conversion.
     */
    public RecentConversion(String measurement, String fromUnit, String toUnit, String fromValue, String toValue ) {
        this.measurement = measurement;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    /**
     * We need a simplified from-to unit conversion text to display in the TableView. This should be simple enough.
     *
     * @return The concatenated from-to units on this conversion.
     */
    public String getUnit() {
        return fromUnit + " -> " + toUnit;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }
}