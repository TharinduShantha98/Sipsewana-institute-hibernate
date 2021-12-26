package lk.ijse.secondSem.hibernate.views.tm;

public class StudentCourseTM {
    String courseId;
    String courseName;
    String courseDuration;
    double courseFee;
    String date;
    String time;

    public StudentCourseTM(String courseId,
                           String courseName, String courseDuration, double courseFee, String date, String time) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
        this.date = date;
        this.time = time;
    }

    public StudentCourseTM() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
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
