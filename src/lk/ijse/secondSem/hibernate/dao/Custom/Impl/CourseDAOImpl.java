package lk.ijse.secondSem.hibernate.dao.Custom.Impl;


import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.Serializable;

public class CourseDAOImpl implements CourseDAO {

    public  SessionFactory sessionFactory;

   public CourseDAOImpl(){
      this.sessionFactory = FactoryConfiguration.getSessionFactory();

   }


    @Override
    public boolean addCourse(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable save =session.save(course);
        transaction.commit();
        if(save!= null){
            return true;
        }
        return false;

    }
}
