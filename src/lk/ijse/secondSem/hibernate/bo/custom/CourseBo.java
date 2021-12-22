package lk.ijse.secondSem.hibernate.bo.custom;

import lk.ijse.secondSem.hibernate.dto.CourseDTO;

import java.util.List;

public interface CourseBo {
    public boolean addCourse(CourseDTO courseDTO);
    CourseDTO searchCourse(String courseId);
    boolean deleteCourse(CourseDTO courseDTO);
    List<CourseDTO> getAll();
    boolean updateCourse(CourseDTO courseDTO);







}
