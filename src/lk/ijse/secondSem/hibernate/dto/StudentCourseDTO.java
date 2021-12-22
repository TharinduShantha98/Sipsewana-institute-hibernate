package lk.ijse.secondSem.hibernate.dto;

import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.entity.Student;

public class StudentCourseDTO{

    private long id;
    private Student student;
    private Course course;
    private String date;
    private  String time;


    public StudentCourseDTO(long id, Student student, Course course, String date, String time) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.date = date;
        this.time = time;
    }

    public StudentCourseDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
