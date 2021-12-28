package lk.ijse.secondSem.hibernate.bo.custom;

import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Student;


import java.util.List;

public interface StudentBo {
    boolean studentAddCourse(StudentDTO studentDTO, List<CourseDTO> studentCoursesDTo, String data,String time);
    List<StudentDTO> getAllStudent();
    boolean updateStudent(StudentDTO studentDTO);
}
