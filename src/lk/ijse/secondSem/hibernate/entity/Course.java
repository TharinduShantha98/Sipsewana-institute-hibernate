package lk.ijse.secondSem.hibernate.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "course")
public class Course {
    @Id
    private String programId;
    private String program;
    private String Duration;
    private double fee;

    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses = new HashSet<StudentCourse>();



    public Course(String programId, String program, String duration, double fee) {
        this.programId = programId;
        this.program = program;
        Duration = duration;
        this.fee = fee;
    }

    public Course() {
    }

    public Course(String programId, String program, String duration,
                  double fee, Set<StudentCourse> studentCourses) {
        this.programId = programId;
        this.program = program;
        Duration = duration;
        this.fee = fee;
        this.studentCourses = studentCourses;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "programId='" + programId + '\'' +
                ", program='" + program + '\'' +
                ", Duration='" + Duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
