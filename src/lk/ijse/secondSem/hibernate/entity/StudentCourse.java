package lk.ijse.secondSem.hibernate.entity;


import javax.persistence.*;

@Entity
@Table(name = "student_course")
public class StudentCourse {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;


    private String date;
    private  String time;


    public StudentCourse(long id, Student student, Course course, String date, String time) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.date = date;
        this.time = time;
    }

    public StudentCourse() {
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
