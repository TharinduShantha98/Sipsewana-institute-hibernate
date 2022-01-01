package lk.ijse.secondSem.hibernate.bo;

import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentCourseBoImpl;

public class BOFactory {
      private  static  BOFactory boFactory;


      private BOFactory(){


      }


      public static BOFactory getBoFactory(){

          if(boFactory == null){
              boFactory = new BOFactory();
          }

          return boFactory;
      }



      public enum BOTypes{

          COURSE, STUDENT, STUDENT_COURSE

      }


      public SuperBO getBO(BOTypes types){
          switch (types){
              case COURSE:
                  return new CourseBoImpl();
              case STUDENT:
                  return new StudentBoImpl();
              case STUDENT_COURSE:
                  return new StudentCourseBoImpl();
              default:
                  return null;


          }




      }

}
