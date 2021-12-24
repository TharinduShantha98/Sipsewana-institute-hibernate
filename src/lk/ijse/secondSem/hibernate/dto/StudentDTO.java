package lk.ijse.secondSem.hibernate.dto;

public class StudentDTO {
    private long studentId;
    private String studentFName;
    private String studentLName;
    private String Address;
    private String idNumber;
    private String gender;






    public StudentDTO(long studentId, String studentFName,
                      String studentLName, String address, String idNumber, String gender) {
        this.studentId = studentId;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        Address = address;
        this.idNumber = idNumber;
        this.gender = gender;
    }

    public StudentDTO() {
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
