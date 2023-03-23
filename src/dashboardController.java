import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class dashboardController {

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_date;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_lastName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_position;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button salary_btn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableColumn<?, ?> salary_col_employeeID;

    @FXML
    private TableColumn<?, ?> salary_col_firstName;

    @FXML
    private TableColumn<?, ?> salary_col_lastName;

    @FXML
    private TableColumn<?, ?> salary_col_position;

    @FXML
    private TableColumn<?, ?> salary_col_salary;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private TableView<?> salary_tableView;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Label username;

    @FXML
    void addEmployeeAdd(ActionEvent event) {

    }

    @FXML
    void addEmployeeDelete(ActionEvent event) {

    }

    @FXML
    void addEmployeeGenderList(ActionEvent event) {

    }

    @FXML
    void addEmployeeInsertImage(MouseEvent event) {

    }

    @FXML
    void addEmployeePositionList(ActionEvent event) {

    }

    @FXML
    void addEmployeeReset(ActionEvent event) {

    }

    @FXML
    void addEmployeeSearch(KeyEvent event) {

    }

    @FXML
    void addEmployeeSelect(MouseEvent event) {

    }

    @FXML
    void addEmployeeUpdate(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();

    try {
        if(option.get().equals(ButtonType.OK)) {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            // Lambda expression (had to point this out because this was a big moment)

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            
        }
    } catch(Exception e) { 
        e.printStackTrace(); 
    }
    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void salaryReset(ActionEvent event) {

    }

    @FXML
    void salarySelect(MouseEvent event) {

    }

    @FXML
    void salaryUpdate(ActionEvent event) {

    }

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);


        } else if (event.getSource() == addEmployee_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);

        }

    }

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private ObservableList<employeeData> addEmployeeListData() {

        //  queries into the "Employee" table.

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = dbConnection.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData eData;

            //  Allows for the data in the SQL database to be accessed via table name.

            while(result.next()) {
                eData = new employeeData(result.getInt("employee_id"), 
                 result.getString("firstName"), 
                 result.getString("lastName"), 
                 result.getString("gender"), 
                 result.getString("phoneNum"), 
                 result.getString("position"), 
                 result.getDate("date"));
            }
        } catch (Exception e) {e.printStackTrace();}
         
        return listData;

    }

    private ObservableList<employeeData> addEmployeeList;
    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        // following code hopefully updates the values on the app with the data thats in the SQL database.

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addEmployee_tableView.setItems(addEmployeeList);
    }

    public void addEmployeeSelect() {
        employeeData eData = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1) {return;}

        addEmployee_employeeID.setText(String.valueOf(eData.getEmployeeId()));
        addEmployee_firstName.setText(eData.getFirstName());
        addEmployee_lastName.setText(eData.getLastName());
        addEmployee_phoneNum.setText(eData.getPhoneNum());

    }

    public void addEmployeeAdd() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee "
        + "(employee_id, firstName, lastName, gender, phoneNum, position, date) "
        + "VALUES(?,?,?,?,?,?,?)";

        connect = dbConnection.getConnection();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, addEmployee_employeeID.getText());
            prepare.setString(2, addEmployee_firstName.getText());
            prepare.setString(3, addEmployee_lastName.getText());
            prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem()); // Has to be converted to string.
            prepare.setString(5, addEmployee_col_gender.getText());
            prepare.setString(6, addEmployee_phoneNum.getText());
            prepare.setString(7, String.valueOf(sqlDate));


        } catch (Exception e) {e.printStackTrace();}
     }

    public void setUsername() {
        
        // Sets label to the name used to login.

        username.setText(getData.username); 
    }

    private double x = 0;
    private double y = 0;

    public void initialize(URL location, ResourceBundle resources) {
        addEmployeeShowListData();
    }

}
