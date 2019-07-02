/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

/**
 *
 * @author j393554
 */
public class MeasurementsCoordinator {
    private static final String[] MEASUREMENTS = {
        "Length",
        "Weight",
        "Temperature",
        "Time",
        "Currency"
    };

    /**
     * When called, takes the desired measurement and retrieves the units used and set as values to the unit comboBoxes
     *
     * @param measurement The target measurement to retrieve units from.
     * @param cbFromUnit The comboBox to alter.
     * @param cbToUnit The comboBox to alter.
     */
    public MeasurementsCoordinator(String measurement, ComboBox<String> cbFromUnit, ComboBox<String> cbToUnit ) {
        String[] units = new String[0];

        switch (measurement) {
            case "Length" :
                units = Length.Units();
                break;
            case "Weight" :
                units = Weight.Units();
                break;
            case "Temperature" :
                units = Temperature.Units();
                break;
            case "Time" :
                units = Time.Units();
                break;
            case "Currency" :
                units = Currency.Units();
                break;
        }

        // If we have enough units (at least 1), render it to the comboBoxes
        if ( units.length > 0 ) {
            cbFromUnit.setItems( FXCollections.observableArrayList( units ) );
            cbToUnit.setItems( FXCollections.observableArrayList( units ) );

            cbFromUnit.getSelectionModel().selectFirst();
            cbToUnit.getSelectionModel().select(1);
        }
    }

    /**
     * Returns the supported measurements of this application in a String array.
     *
     * @return The list of measurements.
     */
    public static String[] measurements() {
        return MEASUREMENTS;
    }
}
