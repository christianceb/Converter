/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author j393554
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button btnConvert;
    @FXML
    private ComboBox<String> cbFromUnit;
    @FXML
    private ComboBox<String> cbToUnit;
    @FXML
    private Button btnReverse;
    @FXML
    private ListView<String> lvConvertHistory;
    @FXML
    private TextField tfFromValue;
    @FXML
    private TextField tfToValue;
    @FXML
    private ComboBox<String> cbMeasurements;
    @FXML
    private TableView<RecentConversion> tvConvertHistory;

    // Set precision to 5 decimal places to ensure a meaningful answer when converting second to day (singular)
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("#.#####");

    /**
     * Code to run upon FXML initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets items in measurements, selects the first measurement and trigger change of measurement.
        cbMeasurements.setItems( FXCollections.observableArrayList( MeasurementsCoordinator.measurements() ) );
        cbMeasurements.getSelectionModel().selectFirst();
        measurementChange();

        // Rename placeholder text in Conversion History to a more relevant message.
        tvConvertHistory.setPlaceholder( new Label("No recent conversions") );
    }

    /**
     * Commence conversion of provided values.
     *
     * @param event
     */
    @FXML
    private void handleConvert(ActionEvent event) {
        // Validate input(s) first.
        if ( validate() ) {
            convert();
        }
    }

    /**
     * Change units of measurements to the relevant units on the selected measurement. The original parameter was removed
     * so this function/event handler can be reused.
     */
    @FXML
    private void measurementChange() {
        new MeasurementsCoordinator( cbMeasurements.getValue(), cbFromUnit, cbToUnit );

        // Clear user input and converted value.
        clearValues();
    }

    /**
     * Reverse the from-to unit and values. Despite this block doing conversions, we will not put them in the conversion
     * history.
     *
     * @param event
     */
    @FXML
    public void handleReverse(ActionEvent event) {
        // There is no need to convert something if it's empty or if values exist on both from/to fields.
        if ( ( tfFromValue.getText().isEmpty() && tfToValue.getText().isEmpty() ) || ( !tfFromValue.getText().isEmpty() && !tfToValue.getText().isEmpty() ) ) {
            reverse();
        } else {
            // Triggered when either from field is empty.
            if ( tfFromValue.getText().isEmpty() ) {
                reverse();
                convert();
                reverse();
            } else if ( validate() ) {
                // Convert existing value and reverse.
                convert();
                reverse();
            }
        }

    }

    /**
     * One-stop-shop validating the "From" value and let the user know something is wrong with the input by setting a
     * red border to the "From" value field.
     *
     * @return true if the validation was successful and resets the border color of the From field regardless of previous state, otherwise false and set border of From field to red.
     */
    public boolean validate() {
        // Is from field empty?
        if ( ! isNumeric( tfFromValue.getText() ) ) {
            tfFromValue.setStyle("-fx-border-color: #f00");
            return false;
        }

        // Ensure that we wipe traces of the error indicator earlier (if any)
        tfFromValue.setStyle("");
        return true;
    }

    /**
     * Converts the "From" value in the active conversion field to the respective selected "To" unit.
     */
    public void convert() {
        String measurement = cbMeasurements.getValue();
        Double fromValue = Double.parseDouble( tfFromValue.getText() );

        String fromUnit = cbFromUnit.getValue();
        String toUnit = cbToUnit.getValue();

        // In case the units are the same, no need to convert. Just apply fromValue to toValue
        Double result = fromValue;

        if ( fromUnit != toUnit ) {
            result = ConversionCoordinator.convert( measurement, fromValue, fromUnit, toUnit );
        }

        // Format value to cap decimal places and set result to "To" field.
        tfToValue.setText(DECIMAL_FORMATTER.format(result));

        // Store successful conversion to conversion history.
        ObservableList<RecentConversion> convertHistoryData = tvConvertHistory.getItems();
        convertHistoryData.add( new RecentConversion(
            measurement,
            fromUnit,
            toUnit,
            DECIMAL_FORMATTER.format(fromValue),
            DECIMAL_FORMATTER.format(result)
        ) );
        tvConvertHistory.scrollTo( convertHistoryData.size() - 1 ); // Scroll to bottom of list.
    }

    /**
     * Reverse the units and the values in the active conversion fields.
     */
    public void reverse() {
        String fromUnit = cbFromUnit.getValue();
        String fromValue = tfFromValue.getText();

        String toUnit = cbToUnit.getValue();
        String toValue = tfToValue.getText();

        cbToUnit.getSelectionModel().select(fromUnit);
        cbFromUnit.getSelectionModel().select(toUnit);

        tfFromValue.setText(toValue);
        tfToValue.setText(fromValue);
    }

    /**
     * Clears "From" and "To" fields in active conversion fields.
     */
    public void clearValues() {
        tfFromValue.clear();
        tfToValue.clear();
    }

    /**
     * Simple way to validate if a number is valid. Note that other radix is accepted so don't be surprised if it accepts 0xFF.
     *
     * @param number
     * @return true if the number is numeric, otherwise false
     */
    public static boolean isNumeric( String number ) {
        try {
            Double.parseDouble( number );
            return true;
        } catch ( Exception error ) {
            return false;
        }
    }

    /**
     * Sets the measurement, units and values of the selected conversion to the active conversion fields.
     *
     * Note that due to limitations in the MouseEvent or a solution not found yet, there is no reliable for us to
     * determine if the clicked area as returned by mouseEvent.getTarget() is a row with data in the conversion history.
     * Which means that clicking on ANY part of the TableView element that is not handled by the if condition below will
     * cause the current highlighted/selected row in the Table view to be applied to the active conversion fields.
     *
     * The only reliable way to select a convert history row and apply it to the active conversion fields is to click on
     * the row you want to use but DO NOT click on the text of the row as getTarget() will return the label and not the
     * row, essentially throwing a wrench into the if condition below and going to the catch block.
     *
     * @param mouseEvent
     */
    public void selectConvertHistory(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.getTarget().getClass() != Label.class && mouseEvent.getTarget().getClass() != LabeledText.class) {
                RecentConversion recentConversion = tvConvertHistory.getSelectionModel().getSelectedItem();

                cbMeasurements.getSelectionModel().select(recentConversion.getMeasurement());
                cbFromUnit.getSelectionModel().select(recentConversion.getFromUnit());
                cbToUnit.getSelectionModel().select(recentConversion.getToUnit());
                tfFromValue.setText(recentConversion.getFromValue());
                tfToValue.setText(recentConversion.getToValue());
            }
        }
        catch ( Exception error ) {
            System.out.print("Error when clicking on an unexpected area of the TableView");
        }
    }
}
