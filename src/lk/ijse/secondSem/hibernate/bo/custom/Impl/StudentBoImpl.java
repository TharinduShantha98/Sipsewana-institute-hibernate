package lk.ijse.secondSem.hibernate.bo.custom.Impl;

import lk.ijse.secondSem.hibernate.bo.custom.StudentBo;
import lk.ijse.secondSem.hibernate.controller.StudentAddFormController;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StudentBoImpl  implements StudentBo {
    SessionFactory sessionFactory;

    public StudentBoImpl(){
        sessionFactory = FactoryConfiguration.getSessionFactory();

    }

    @Override
    public boolean studentAddCourse(StudentDTO studentDTO, List<CourseDTO> studentCourses,String date,String time) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = convertStudentType(studentDTO);
        ArrayList<Course> courseArrayList = convertCourseType(studentCourses);



        List<StudentCourse> studentCourses1 = new ArrayList<>();
        for (Course c1 : courseArrayList
             ) {
            studentCourses1.add(new StudentCourse(student,c1,date,time));
        }


        for (StudentCourse s1: studentCourses1
             ) {
            session.save(s1);
        }


        transaction.commit();
        return true;










      /*  for(CourseDTO courseDTO1: courseDTO){
            courseArrayList.add(new Course(courseDTO1.getProgramId(),courseDTO1.getProgram(),
                    courseDTO1.getDuration(),courseDTO1.getFee()));
        }


*//* =====================================================================
   *//*
        for (StudentCourse s1: studentCourses1
             ) {
           student.getStudentCourses().add(s1);
        }
 *//* =====================================================================       *//*
        for (StudentCourse s1: studentCourses1
             ) {
            s1.setStudent(student);
        }

        session.save(student);

        for (StudentCourse s1 : studentCourses1
             ) {
            Serializable save = session.save(s1);
            if(save!= null){
                transaction.rollback();
                return false;

            }
        }



        transaction.commit();
        return true;
*/

    }


    private Student convertStudentType(StudentDTO studentDTO){
        if(studentDTO!= null){
            return new Student(studentDTO.getStudentId(),
                    studentDTO.getStudentFName(),studentDTO.getStudentLName(),
                    studentDTO.getAddress(),studentDTO.getIdNumber(),studentDTO.getGender());
        }

        return null;


    }


    private ArrayList<Course> convertCourseType(List<CourseDTO> courseDTOList){

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (CourseDTO c1: courseDTOList
             ) {
            courseArrayList.add(new Course(c1.getProgramId(),c1.getProgram(),c1.getDuration(),c1.getFee()));
        }

        return courseArrayList;
    }

}