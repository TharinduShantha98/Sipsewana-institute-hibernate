package lk.ijse.secondSem.hibernate.dao.Custom;

import lk.ijse.secondSem.hibernate.dao.CrudDao;

import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public interface StudentDetailDAO extends CrudDao<StudentCourse,String> {

    List<StudentCourse> studentSearch(String s);
    public boolean updateDetail(List<StudentCourse> studentCourses);
    boolean studentDelete(long id);
    public boolean addNewStudentCourse(Student student, List<StudentCourse> studentCourseList);

}
