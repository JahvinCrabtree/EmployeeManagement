import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class adminController {

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    // DATABASE TOOLS.

    private double x = 0;
    private double y = 0;

    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    
    public void loginAdmin() {
        String sql = "SELECT * FROM admin_table WHERE username = ? and password = ?";
        connect = dbConnection.getConnection();

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, usernameField.getText());
            prepare.setString(2, passwordField.getText());

            result = prepare.executeQuery();
            Alert alert;

            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
            }
            else if (result.next()){

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfull Login.");

                loginBtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getSceneX() - x);
                    stage.setX(event.getSceneY() - y);
                });

                stage.setScene(scene);
                stage.show();
            } 
            else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username or Password is incorrect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 


}
