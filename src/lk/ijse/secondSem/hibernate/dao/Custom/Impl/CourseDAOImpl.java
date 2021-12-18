package lk.ijse.secondSem.hibernate.dao.Custom.Impl;


import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        if(save!= null){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(Course course) {
        return false;
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    @Override
    public Course search(String s) {
        Course course;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        course = session.get(Course.class, s);
        transaction.commit();
        return course;

    }
}
