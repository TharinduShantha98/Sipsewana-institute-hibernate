package lk.ijse.secondSem.hibernate.dao.Custom.Impl;

import lk.ijse.secondSem.hibernate.dao.Custom.StudentDAO;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl  implements StudentDAO {
    SessionFactory sessionFactory;
    public StudentDAOImpl() {
         sessionFactory = FactoryConfiguration.getSessionFactory();

    }

    @Override
    public boolean add(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "FROM Student";
            Query<Student> query = session.createQuery(sql);
            List<Student> list = query.list();
            transaction.commit();
            return list;
        }catch (Exception e){
            return  null;

        }


    }

    @Override
    public Student search(String s) {
        return null;
    }
}
