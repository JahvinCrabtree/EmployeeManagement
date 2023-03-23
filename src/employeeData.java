import java.sql.Date;

public class employeeData {
    
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String position;
    private Date date;

    public employeeData(Integer employeeId, String firstName, String lastName, String gender, String phoneNum, String position, Date date) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.position = position;
    }

    // getters for the private data.

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPosition() {
        return position;
    }

    public Date getDate() {
        return date;
    }
}
