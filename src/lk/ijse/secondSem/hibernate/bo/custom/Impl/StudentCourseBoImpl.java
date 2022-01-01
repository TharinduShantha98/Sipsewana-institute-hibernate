package lk.ijse.secondSem.hibernate.bo.custom.Impl;

import lk.ijse.secondSem.hibernate.bo.custom.StudentCourseBO;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.StudentDetailDAOImpl;
import lk.ijse.secondSem.hibernate.dao.Custom.StudentDetailDAO;
import lk.ijse.secondSem.hibernate.dao.DAOFactory;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseBoImpl implements StudentCourseBO {

    private final StudentDetailDAO studentDetailDAO = (StudentDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_DETAIL);

   /* StudentDetailDAO studentDetailDAO = new StudentDetailDAOImpl();*/



    @Override
    public List<StudentCourseDTO> search(String id) {
        List<StudentCourse> studentCourses = studentDetailDAO.studentSearch(id);
        System.out.println("bo "+studentCourses.size());


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

    @Override

    /*====================== update only student detail=============================*/

    public boolean updateStudentCourseDetail(List<StudentCourseDTO> studentCourse) {
      StudentCourseDTO studentCourseDTO = studentCourse.get(0);


        StudentDTO studentDTO = studentCourseDTO.getStudent();
        Student student1 = new Student();
        student1.setIdNumber(studentDTO.getIdNumber());
        student1.setStudentFName(studentDTO.getStudentFName());
        student1.setStudentLName(studentDTO.getStudentLName());
        student1.setIdNumber(studentDTO.getIdNumber());
        student1.setAddress(studentDTO.getAddress());
        student1.setGender(studentDTO.getGender());


        List<Course> courses = new ArrayList<>();

        for (StudentCourseDTO s1: studentCourse
             ) {

            courses.add(new Course(s1.getCourse().getProgramId(),
                    s1.getCourse().getProgram(),
                    s1.getCourse().getDuration(),
                    s1.getCourse().getFee()));

        }

        List<StudentCourse> studentCourseList = new ArrayList<>();
        for(int i =0; i < courses.size(); i++){
           studentCourseList.add(new StudentCourse(
                   student1,courses.get(i),studentCourse.get(i).getDate(),studentCourse.get(i).getTime()));

        }

        boolean b = studentDetailDAO.updateDetail(studentCourseList);

        return b;


    }


   /* ================== delete student and delete his all courses==================*/

    @Override
    public boolean deleteStudent(long id) {
        boolean b = studentDetailDAO.studentDelete(id);
        return b;

    }


}
