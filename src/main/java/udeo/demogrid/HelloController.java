package udeo.demogrid;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    //relaciones con los componentes de la vista
    @FXML
    private Label welcomeText;
    @FXML
    public GridPane gvDetails;
    @FXML
    private TextField txtNIT;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    //Lista para almacenar los usuarios
    private final List<User> users = new ArrayList<>();
    //Índice del usuario seleccionado en el grid
    private int selectedIndex = -1;

    @FXML
    public void initialize() {
        //Init o inicialización de datos de prueba
        users.add(new User("73489347", "Doe", "me@gmail.com"));
        users.add(new User("23498", "Smith", "you@gmail.com"));
        users.add(new User("234324", "Johnson", "she@gmail.com"));
        users.add(new User("233232", "Williams", "wsw@gmail.com"));
        refreshGrid();
    }

    @FXML
    protected void onAddButtonClick() {
        //Leer valores de los campos
        String nit = txtNIT.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        //Validar que no estén vacíos
        if (nit.isEmpty() || name.isEmpty() || email.isEmpty()) {
            showAlert("Validation", "All fields are required.");
            return;
        }
        //Agregando el nuevo usuario a la lista
        users.add(new User(nit, name, email));
        clearForm();
        refreshGrid();
    }

    @FXML
    protected void onUpdateButtonClick() {
        if (selectedIndex < 0 || selectedIndex >= users.size()) {
            showAlert("Selection", "Please select a user from the grid first.");
            return;
        }

        String nit = txtNIT.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();

        if (nit.isEmpty() || name.isEmpty() || email.isEmpty()) {
            showAlert("Validation", "All fields are required.");
            return;
        }

        User user = users.get(selectedIndex);
        user.setNIT(nit);
        user.setName(name);
        user.setEmail(email);

        selectedIndex = -1;
        clearForm();
        refreshGrid();
    }

    @FXML
    protected void onDeleteButtonClick() {
        if (selectedIndex < 0 || selectedIndex >= users.size()) {
            showAlert("Selection", "Please select a user from the grid first.");
            return;
        }

        users.remove(selectedIndex);
        selectedIndex = -1;
        clearForm();
        refreshGrid();
    }

    private void refreshGrid() {
        gvDetails.getChildren().clear();

        gvDetails.add(new Label("NIT"), 0, 0);
        gvDetails.add(new Label("Name"), 1, 0);
        gvDetails.add(new Label("Email"), 2, 0);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            int rowIndex = i + 1;

            Label lblNit = new Label(user.getNIT());
            Label lblName = new Label(user.getName());
            Label lblEmail = new Label(user.getEmail());

            final int index = i;
            lblNit.setOnMouseClicked(e -> selectUser(index));
            lblName.setOnMouseClicked(e -> selectUser(index));
            lblEmail.setOnMouseClicked(e -> selectUser(index));

            gvDetails.add(lblNit, 0, rowIndex);
            gvDetails.add(lblName, 1, rowIndex);
            gvDetails.add(lblEmail, 2, rowIndex);
        }
    }

    private void selectUser(int index) {
        selectedIndex = index;
        User user = users.get(index);
        txtNIT.setText(user.getNIT());
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
    }

    private void clearForm() {
        //Limpiar los campos de texto
        txtNIT.clear();
        txtName.clear();
        txtEmail.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
