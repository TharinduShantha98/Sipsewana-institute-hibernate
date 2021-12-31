package lk.ijse.secondSem.hibernate.dao.Custom.Impl;

import lk.ijse.secondSem.hibernate.dao.Custom.StudentDetailDAO;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class StudentDetailDAOImpl implements StudentDetailDAO {
    SessionFactory sessionFactory;


    public StudentDetailDAOImpl() {
        this.sessionFactory = FactoryConfiguration.getSessionFactory();

    }



    @Override
    public boolean add(StudentCourse studentCourse) {
        return false;
    }

    @Override
    public boolean update(StudentCourse studentCourse) {
        return false;
    }

    @Override
    public boolean delete(StudentCourse studentCourse) {

        return false;
    }

    @Override
    public List<StudentCourse> getAll() {
        return null;
    }

    @Override
    public StudentCourse search(String s) {
        return null;
    }


    @Override
    public List<StudentCourse> studentSearch(String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            NativeQuery<StudentCourse> nativeQuery =
                    session.createNativeQuery("SELECT * FROM student_course WHERE student_id = ?",StudentCourse.class);
            NativeQuery<StudentCourse> studentCourseNativeQuery = nativeQuery.setParameter(1, s);
            List<StudentCourse> list = studentCourseNativeQuery.list();
            System.out.println("dao "+ list.size());
            if(!list.isEmpty()){
                System.out.println("dao "+ list.size());
                return list;

            }



        }catch (Exception e){
            e.printStackTrace();
            return null;


        }

        return null;
    }



    @Override
    public boolean updateDetail(List<StudentCourse> studentCourses){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        for (StudentCourse s1: studentCourses
             ) {
            session.update(s1);
        }

        
        transaction.commit();


        return true;

    }

    @Override
    public boolean studentDelete(long id) {
        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0");

           /* NativeQuery nativeQuery = session.createNativeQuery("DELETE T FROM student T INNER JOIN student_course" +
                    " ON student_course.student_id = T.studentId WHERE student_course.student_id =?", Student.class);

*/
            Student s1 = new Student();
            s1.setStudentId(id);

            NativeQuery nativeQuery = session.createNativeQuery("DELETE FROM student_course WHERE student_id = ?");
            int i = nativeQuery.setParameter(1, id).executeUpdate();



            if(i!=0){
                session.delete(s1);
                session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1");
                transaction.commit();


                return true;
            }else{
                transaction.rollback();
                return false;
            }


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }


}
