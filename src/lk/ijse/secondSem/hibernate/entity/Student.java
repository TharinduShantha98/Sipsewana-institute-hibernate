package lk.ijse.secondSem.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private long studentId;
    private String studentFName;
    private String studentLName;
    private String Address;
    private String idNumber;
    private String gender;
    private double totalFee;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private  List<StudentCourse> studentCourses = new ArrayList<>();


    public Student(long studentId, String studentFName, String studentLName, String address,
                   String idNumber, String gender, ArrayList<StudentCourse> studentCourses,double totalFee) {
        this.studentId = studentId;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        Address = address;
        this.idNumber = idNumber;
        this.gender = gender;
        this.studentCourses = studentCourses;
        this.totalFee =totalFee;
    }

    public Student(long studentId, String studentFName, String studentLName,
                   String address, String idNumber, String gender,double totalFee) {
        this.studentId = studentId;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        Address = address;
        this.idNumber = idNumber;
        this.gender = gender;
        this.totalFee = totalFee;
    }

    public Student() {
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

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}
