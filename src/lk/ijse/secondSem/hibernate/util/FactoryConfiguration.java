package lk.ijse.secondSem.hibernate.util;


import lk.ijse.secondSem.hibernate.entity.Course;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryConfiguration {
    private  static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        StandardServiceRegistry sReg=new StandardServiceRegistryBuilder()
                .loadProperties("lk/ijse/secondSem/hibernate/hibernate.properties")
                .build();

        Metadata mData=new MetadataSources(sReg)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(StudentCourse.class)
                .getMetadataBuilder().build();

        return mData.getSessionFactoryBuilder().build();

    }

    public  static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
