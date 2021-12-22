package lk.ijse.secondSem.hibernate.dao.Custom.Impl;


import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.Serializable;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    public  SessionFactory sessionFactory;

   public CourseDAOImpl(){
      this.sessionFactory = FactoryConfiguration.getSessionFactory();

   }

    @Override
    public boolean add(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable save =session.save(course);
        transaction.commit();
        session.close();
        if(save!= null){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Course course) {

       try {
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();
           session.update(course);
           transaction.commit();
           return true;

       }catch (Exception a){


       }

        return false;

    }

    @Override
    public boolean delete(Course course) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
            return true;
        }catch (Exception e){

        }

        return false;


    }

    @Override
    public List<Course> getAll() {
       try {
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();
           NativeQuery<Course> nativeQuery = session.createNativeQuery("SELECT * FROM course", Course.class);
           List<Course> list = nativeQuery.list();
           return list;
       }catch (Exception e){


       }

        return null;

    }

    @Override
    public Course search(String s) {
        Course course;

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            course = session.get(Course.class, s);
            transaction.commit();
            return course;

        }catch (Exception e){

        }
       return null;

    }
}
