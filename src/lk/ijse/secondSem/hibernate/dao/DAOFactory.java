package lk.ijse.secondSem.hibernate.dao;

import lk.ijse.secondSem.hibernate.bo.SuperBO;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.CourseDAOImpl;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.secondSem.hibernate.dao.Custom.Impl.StudentDetailDAOImpl;

public class DAOFactory {

    private  static DAOFactory daoFactory;

    private DAOFactory(){



    }



    public static DAOFactory getDaoFactory(){

        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;

    }



    public  enum  DAOTypes{
        COURSE, STUDENT , STUDENT_DETAIL

    }


    public SuperDAO getDAO(DAOTypes types){

        switch (types){
            case STUDENT:
              return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case STUDENT_DETAIL:
                return new StudentDetailDAOImpl();
            default:
                return null;


        }


    }


}
