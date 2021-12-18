package lk.ijse.secondSem.hibernate.dto;

public class CourseDTO {

    String programId;
    String program;
    String Duration;
    double fee;


    public CourseDTO(String programId, String program, String duration, double fee) {
        this.programId = programId;
        this.program = program;
        Duration = duration;
        this.fee = fee;
    }

    public CourseDTO() {
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
