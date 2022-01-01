package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.secondSem.hibernate.bo.BOFactory;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentCourseBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.StudentBo;
import lk.ijse.secondSem.hibernate.bo.custom.StudentCourseBO;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.entity.StudentCourse;
import lk.ijse.secondSem.hibernate.util.FactoryConfiguration;
import lk.ijse.secondSem.hibernate.views.tm.StudentCourseTM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDetailFormController {

    public AnchorPane StudentD;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentFirstName;
    public JFXTextField txtStudentLastName;
    public JFXTextField txtAddress;
    public JFXTextField txtGender;
    public JFXTextField txtIdNumber;
    public JFXTextField txtTotalFee;
    public TableView tblCourseDetail;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colDuration;
    public TableColumn colCourseFee;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colFname;
    public TableColumn colLname;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colIdNumber;
    public TableColumn colTotalFee;


    private final StudentCourseBO studentCourseBO = (StudentCourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT_COURSE);
    private final StudentBo studentBo = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


   /* StudentCourseBO studentCourseBO = new StudentCourseBoImpl();
    StudentBo studentBo = new StudentBoImpl();*/

    public void initialize(){
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));





        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("studentFName"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("studentLName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colTotalFee.setCellValueFactory(new PropertyValueFactory<>("totalFee"));



        setStudentTbl();


      /*  cache();
        cache2();*/
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../views/dashBordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.StudentD.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

/*=================== search student ==========================*/
    List<StudentCourseDTO> studentCourseDTOList = new ArrayList<>();
    public void searchStudentOnAction(ActionEvent actionEvent) {
        try {
            List<StudentCourseDTO> searchStudent = studentCourseBO.search(txtStudentId.getText());
            studentCourseDTOList.addAll(searchStudent);

            int size = searchStudent.size();
            System.out.println(size);

            List<CourseDTO> courseDTOList = new ArrayList<>();


            if(!searchStudent.isEmpty()){
                StudentCourseDTO search = searchStudent.get(0);
                String date = search.getDate();
                String time = search.getTime();



                txtStudentFirstName.setText(search.getStudent().getStudentFName());
                txtStudentLastName.setText(search.getStudent().getStudentLName());
                txtAddress.setText(search.getStudent().getAddress());
                txtGender.setText(search.getStudent().getGender());
                txtIdNumber.setText(search.getStudent().getIdNumber());
                txtTotalFee.setText(String.valueOf(search.getStudent().getTotalFee()));


                for (StudentCourseDTO s1: searchStudent
                ) {
                    courseDTOList.add(new CourseDTO(s1.getCourse().getProgramId(),
                            s1.getCourse().getProgram(),
                            s1.getCourse().getDuration(),
                            s1.getCourse().getFee()));


                }
                System.out.println(courseDTOList.size());
                setTblCourseDetail(courseDTOList,date, time);


            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Not student available ");
                alert.show();

            }



        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Not student available ");
            alert.show();

        }

    }


/*====================== table studentDetail===========================*/
    public void setTblCourseDetail(List<CourseDTO> courseDetail,String date, String time){
        ObservableList<StudentCourseTM> studentCourseTMObservableList
                = FXCollections.observableArrayList();

        for (CourseDTO c1: courseDetail
             ) {

            studentCourseTMObservableList.add(new StudentCourseTM(
                    c1.getProgramId(),
                    c1.getProgram(),
                    c1.getDuration(),
                    c1.getFee(),
                    date,
                    time));
        }


        tblCourseDetail.setItems(studentCourseTMObservableList);

    }

  /*  ===============================table student===============================*/
    public void setStudentTbl(){
        List<StudentDTO> allStudent = studentBo.getAllStudent();

        if(!allStudent.isEmpty()) {
            ObservableList<StudentDTO> studentDTOObservableList = FXCollections.observableArrayList();
            studentDTOObservableList.addAll(allStudent);
            tblStudent.setItems(studentDTOObservableList);
        }else{


        }


    }




/*==========update student =================*/
    public void updateStudentOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(Long.parseLong(txtStudentId.getText()),
                txtStudentFirstName.getText(),
                txtStudentLastName.getText(),
                txtAddress.getText(),
                txtIdNumber.getText(),
                txtGender.getText(),
                Double.parseDouble(txtTotalFee.getText()));

        boolean b = studentBo.updateStudent(studentDTO);

        Alert alert;
        if(b){
            alert = new Alert(Alert.AlertType.CONFIRMATION,"success update");
        }else{
            alert = new Alert(Alert.AlertType.WARNING,"not success update");

        }
        alert.show();
        setStudentTbl();


    }



  /*  ====================delete student======================*/
    public void deleteStudentOnAction(ActionEvent actionEvent) {
        boolean b = studentCourseBO.deleteStudent(Long.parseLong(txtStudentId.getText()));


        Alert alert;
        if(b){
           alert= new Alert(Alert.AlertType.CONFIRMATION," delete success ");

        }else {
            alert = new Alert(Alert.AlertType.WARNING, "delete not success");
        }

        alert.show();

    }






    public void addStudentPageOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../views/StudentAddForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.StudentD.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();



    }




    /*====================second level cache check=================*/

   /* public void cache(){
        SessionFactory sessionFactory= FactoryConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, new Long(12));
        System.out.println(student);


        Student student1 = session.get(Student.class, new Long(12));
        System.out.println(student1);

        transaction.commit();
    }


    public void cache2(){
        SessionFactory sessionFactory= FactoryConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, new Long(12));
        System.out.println(student);




        transaction.commit();
    }
*/





}
