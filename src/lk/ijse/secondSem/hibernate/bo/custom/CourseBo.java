package lk.ijse.secondSem.hibernate.bo.custom;

import lk.ijse.secondSem.hibernate.dto.CourseDTO;

public interface CourseBo {
    public boolean addCourse(CourseDTO courseDTO);
    CourseDTO searchCourse(String courseId);
    boolean deleteCourse(String courseId);







}
