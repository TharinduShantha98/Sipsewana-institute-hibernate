package lk.ijse.secondSem.hibernate.bo.custom;

import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;


import java.util.List;

public interface StudentBo {
    boolean studentAddCourse(StudentDTO studentDTO, List<CourseDTO> studentCoursesDTo, String data,String time);
}
