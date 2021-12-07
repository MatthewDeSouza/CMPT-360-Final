package finalprojectgui;

public class Company {

    private String instructorName, instructorID, instructorDepartment;
    private String departmentName, location, budget;

    protected enum FileName {
        DEPARTMENT,
        INSTRUCTOR,
        NEITHER
    }

    public Company(String employeeName, String employeeID,
            String employeeDepartment, String departmentName, String location,
            String budget) {
        this.instructorName = employeeName;
        this.instructorID = employeeID;
        this.instructorDepartment = employeeDepartment;
        this.departmentName = departmentName;
        this.location = location;
        this.budget = budget;
    }

    public Company() {
        this("", "", "", "", "", "");
    }

    // Getters
    public String getEmployeeName() {
        return instructorName;
    }

    public String getEmployeeID() {
        return instructorID;
    }

    public String getEmployeeDepartment() {
        return instructorDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getLocation() {
        return location;
    }

    public String getBudget() {
        return budget;
    }

    // Setters
    public void setEmployeeName(String employeeName) {
        this.instructorName = employeeName;
    }

    public void setEmployeeID(String employeeID) {
        this.instructorID = employeeID;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.instructorDepartment = employeeDepartment;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocaton(String location) {
        this.location = location;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    // Methods
    public String formatInsertString(FileName FILE) {
        switch (FILE) {
            case INSTRUCTOR: {
                return String.format("%s,%s,%s\n", instructorID, instructorName,
                        instructorDepartment);
            }
            case DEPARTMENT: {
                return String.format("%s,%s,%s\n", departmentName, location,
                        budget);
            }
        }
        return null;
    }
}
