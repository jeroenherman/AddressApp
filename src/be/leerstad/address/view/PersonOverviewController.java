package be.leerstad.address.view;

import be.leerstad.address.MainApp;
import be.leerstad.address.business.Person;
import be.leerstad.address.util.DateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    //
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        firstNameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));


        // Clear person details.
        showPersonDetails(null);

        //Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
// Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared. *
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
// Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            // DateUtil :  converts the birthday into a String
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
// Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    /**
     * Called when the user clicks on the delete button.
     */
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            showNoPersonSelectedWarning();

        }
    }

    @FXML
    /**
     * Called when the user clicks on the Edit button.
     */
    private void handleEditPerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Person toEdit = personTable.getItems().get(selectedIndex);
            mainApp.showPersonEditDialog(toEdit);
        } else {
            showNoPersonSelectedWarning();
        }
    }

    @FXML
    /**
     * Called when user clicks the the New Buttonau
     */
    private void handleNewPerson() {
        Person toAdd = new Person();
        personTable.getItems().add(toAdd);
        mainApp.showPersonEditDialog(toAdd);
    }

    private void showNoPersonSelectedWarning() {
        // Nothing Selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");
        alert.showAndWait();
    }
}

