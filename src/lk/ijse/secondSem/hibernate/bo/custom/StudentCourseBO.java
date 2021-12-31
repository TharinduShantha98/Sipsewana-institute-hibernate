package lk.ijse.secondSem.hibernate.bo.custom;

import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;

import java.util.List;

public interface StudentCourseBO {

    List<StudentCourseDTO> search(String id);
    boolean updateStudentCourseDetail(List<StudentCourseDTO> studentCourse);
    boolean deleteStudent(long id);






}
