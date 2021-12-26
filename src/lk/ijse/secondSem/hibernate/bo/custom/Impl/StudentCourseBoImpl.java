package lk.ijse.secondSem.hibernate.bo.custom.Impl;

import lk.ijse.secondSem.hibernate.bo.custom.StudentCourseBO;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.StudentDetailDAOImpl;
import lk.ijse.secondSem.hibernate.dao.Custom.StudentDetailDAO;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseBoImpl implements StudentCourseBO {

    StudentDetailDAO studentDetailDAO = new StudentDetailDAOImpl();



    @Override
    public List<StudentCourseDTO> search(String id) {
        List<StudentCourse> studentCourses = studentDetailDAO.studentSearch(id);

        if(!studentCourses.isEmpty()){
            StudentCourse studentCourse = studentCourses.get(0);
            String date = studentCourse.getDate();
            String time = studentCourse.getTime();


            Student student = studentCourse.getStudent();
            StudentDTO studentDto = new StudentDTO(student.getStudentId(),
                    student.getStudentFName(),
                    student.getStudentLName(),
                    student.getAddress(),
                    student.getIdNumber(),
                    student.getGender(),
                    student.getTotalFee());


            List<CourseDTO> courseDTOList = new ArrayList<>();

            for (StudentCourse s1: studentCourses
                 ) {
                courseDTOList.add(new CourseDTO(s1.getCourse().getProgramId(),
                        s1.getCourse().getProgram(),
                        s1.getCourse().getDuration(),
                        s1.getCourse().getFee()));
            }

            List<StudentCourseDTO> studentCourses1 = new ArrayList<>();
            for (CourseDTO s1:  courseDTOList
                 ) {
                studentCourses1.add(new StudentCourseDTO(studentDto,s1,date,time));
            }

            return studentCourses1;

        }else{
            return null;
        }


    }
}
