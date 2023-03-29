import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    // this gave me such a wonky error that took me forever to find because it was declared as "?" instead of employeeData. :) :) :) :)
    private TableView<employeeData> salary_tableView;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Label username;

    @FXML
    void addEmployeeAdd(ActionEvent event) {
        addEmployeeAdd();
    }

    @FXML
    void addEmployeeDelete(ActionEvent event) {
        addEmployeeDelete();
    }

    @FXML
    void addEmployeeGenderList(ActionEvent event) {

    }

    // needs to be fixed :(
    @FXML
    void addEmployeeInsertImage(MouseEvent event) {

    }

    @FXML
    void addEmployeePositionList(ActionEvent event) {
        addEmployeePositionList();
    }

    @FXML
    void addEmployeeReset(ActionEvent event) {
        addEmployeeReset();
    }

    @FXML
    void addEmployeeSearch(KeyEvent event) {
        addEmployeeSearch();
    }

    @FXML
    void addEmployeeSelect(MouseEvent event) {
        addEmployeeSelect();
    }

    @FXML
    void addEmployeeUpdate(ActionEvent event) {
        addEmployeeUpdate();
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
            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
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
        } catch (Exception e) {
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

            addEmployeeGenderList();
            addEmployeePositionList();

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);

        }

    }

    // Actions for the drop down boxes on the Employee page.

    private String[] positionList = { "Manager", "Deputy Manager", "Chief of Operations", "Chief of Communication",
            "Supervisor", "Marketer", "HR Assistant" };

    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        addEmployee_position.setItems(listData);
    }

    private String[] genderList = { "Male", "Female", "Other" };

    public void addEmployeeGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : genderList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);
    }

    public void addEmployeeAdd() {
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee "
                + "(employee_id, firstName, lastName, gender, phoneNum, position, date) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = dbConnection.getConnection();

        try {

            Alert alert;

            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null // different because it's a
                                                                                        // comboBox - but it's the same
                                                                                        // principle.
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all blank fields.");
                alert.showAndWait();
            } else {

                String check = "SELECT employee_id FROM employee WHERE employee_id = '"
                        + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message!");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeID.getText() + " already exists.");
                    alert.showAndWait();
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, addEmployee_employeeID.getText()); // Numbers in order of the column they relate to
                                                                        // in the DB
                prepare.setString(2, addEmployee_firstName.getText());
                prepare.setString(3, addEmployee_lastName.getText());
                prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem()); // Has to be
                                                                                                         // converted to
                                                                                                         // string.
                prepare.setString(5, addEmployee_phoneNum.getText());
                prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                prepare.setString(7, String.valueOf(sqlDate));
                prepare.executeLargeUpdate();

                // for the salary page - also inserts the data into the employee_info table.
                
                String insertInfo = "INSERT INTO employee_info "
                + "(employee_id, firstName, lastName, position, salary, date) "
                + "VALUES(?,?,?,?,?,?)";

                prepare = connect.prepareStatement(insertInfo);
                prepare.setString(1, addEmployee_employeeID.getText());
                prepare.setString(2, addEmployee_firstName.getText());
                prepare.setString(3, addEmployee_lastName.getText());
                prepare.setString(4, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                prepare.setString(5, "0.0");
                prepare.setString(6, String.valueOf(sqlDate));
                prepare.executeLargeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message!");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                addEmployeeReset();
                addEmployeeShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeReset() {
        addEmployee_employeeID.setText("");
        addEmployee_firstName.setText("");
        addEmployee_lastName.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_position.getSelectionModel().clearSelection();
        getData.path = "";
    }

    public void addEmployeeSelect() {
        employeeData eData = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addEmployee_employeeID.setText(String.valueOf(eData.getEmployeeId()));
        addEmployee_firstName.setText(eData.getFirstName());
        addEmployee_lastName.setText(eData.getLastName());
        addEmployee_phoneNum.setText(eData.getPhoneNum());
    }

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<employeeData> addEmployeeListData() {

        // queries into the "Employee" table.

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = dbConnection.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData eData;

            // Allows for the data in the SQL database to be accessed via table name.

            while (result.next()) {
                eData = new employeeData(result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("phoneNum"),
                        result.getString("position"),
                        result.getDate("date")); // Shows the data in the table (Missed this for what ? 4 days or so)
                listData.add(eData); // data would show in SQL but not on the app table.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;

    }

    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        // following code hopefully updates the values on the app with the data thats in
        // the SQL database.

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addEmployee_tableView.setItems(addEmployeeList);
    }

    // allows me to select an employee through the ID and change the details in SQL.
    // same format is used for similar functions such as delete etc
    // with the difference being the SQL statements being changed.

    public void addEmployeeUpdate() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '"
                + addEmployee_firstName.getText() + "', lastName = '"
                + addEmployee_lastName.getText() + "', gender = '"
                + addEmployee_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + addEmployee_phoneNum.getText() + "', position = '"
                + addEmployee_position.getSelectionModel().getSelectedItem() + "', date = '" + sqlDate
                + "' WHERE employee_id ='"
                + addEmployee_employeeID.getText() + "'";

        connect = dbConnection.getConnection();

        try {

            Alert alert;

            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null // different because it's a
                                                                                        // comboBox - but it's the same
                                                                                        // principle.
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all blank fields.");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message!");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to UPDATE Employee ID: " + addEmployee_employeeID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message!");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated!");
                    alert.showAndWait();
                }
            }
            
            addEmployeeShowListData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletes the information from the SQL database from the application. 
    // This is done by simply entering the details of the employee
    // this can be done by just clicking the person on the table view. 
    
    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE employee_id = '"
                + addEmployee_employeeID.getText() + "'";

        connect = dbConnection.getConnection();

        try {

            Alert alert;

            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null 
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all blank fields.");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message!");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to DELETE Employee ID: " + addEmployee_employeeID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message!");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                }
            }
            
            addEmployeeShowListData();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // If the serach key contains the details from an employee, it flags them up in the table view. 
    // the search key being whatever they type into the search box located on the app. 
    
    public void addEmployeeSearch() {
        
        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();

                if(predicateEmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } 
                else if (predicateEmployeeData.getLastName().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if (predicateEmployeeData.getPhoneNum().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if (predicateEmployeeData.getDate().toString().contains(searchKey)){
                    return true;
                }
                else return false;   
            });
        });
        
        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }

    // Sets label to the name used to login.    
    
    public void setUsername() {
        username.setText(getData.username);
    }

    public ObservableList<employeeData> salaryListData() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee_info";

        connect = dbConnection.getConnection();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            employeeData eData;

            while(result.next()) {
                eData = new employeeData(result.getInt("employee_id"),
                result.getString("firstName"),
                result.getString("lastName"),
                result.getString("position"),
                result.getDouble("salary"));
                listData.add(eData);
            }
 
        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }

    private ObservableList<employeeData> salaryList;

    public void salaryShowListData() {
        salaryList = salaryListData();

        salary_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_tableView.setItems(salaryList);

    }
    private double x = 0;
    private double y = 0;

    public void initialize(URL location, ResourceBundle resources) {
        setUsername();
        addEmployeeShowListData();
        addEmployeeGenderList();
        addEmployeePositionList();
    }

}
