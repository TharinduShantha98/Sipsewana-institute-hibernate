package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.StudentBo;
import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentCourseDTO;
import lk.ijse.secondSem.hibernate.dto.StudentDTO;
import lk.ijse.secondSem.hibernate.entity.Student;
import lk.ijse.secondSem.hibernate.util.DateTime;
import lk.ijse.secondSem.hibernate.views.tm.CartTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentAddFormController {


    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtAddress;
    public JFXComboBox<String> cmbCourseId;
    public JFXTextField txtIdNumber;
    public JFXComboBox<String>cmbGender;
    public TableView<CartTM> tblCart;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colFee;
    public TableColumn colCourseDuration;
    public Label lblTotalFee;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane studentA;




    private  CourseDTO courseDTO;

    CourseBo courseBo = new CourseBoImpl();
    StudentBo studentBo = new StudentBoImpl();
    DateTime dateTime = new DateTime();

    public void initialize(){
        setCmbCourseId();
        setCmbGender();

        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        setTimeAndDate();

    }


    public void setCmbCourseId(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<CourseDTO>  courseDTOArrayList = new ArrayList<>();
        courseDTOArrayList.addAll(courseBo.getAll());


        for(int i =0; i < courseDTOArrayList.size(); i++){
            observableList.add(courseDTOArrayList.get(i).getProgramId());
        }

        cmbCourseId.setItems(observableList);

    }

    public void setCmbGender(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Male");
        observableList.add("Female");
        cmbGender.setItems(observableList);

    }


    public void getCourseDetail(){
       courseDTO = courseBo.searchCourse(cmbCourseId.getValue());


    }

    ObservableList<CartTM> cartTMObservableList = FXCollections.observableArrayList();
    public void AddCartOnAction(ActionEvent actionEvent) {
        CartTM cartTM = new CartTM(courseDTO.getProgramId(),
                courseDTO.getProgram(),courseDTO.getDuration(),courseDTO.getFee());
         cartTMObservableList.add(cartTM);
         tblCart.setItems(cartTMObservableList);
         tblCart.refresh();

        createTotalFeeForStudent();

    }
    private void createTotalFeeForStudent(){
        double totalFee =0;

        for (CartTM e1:cartTMObservableList
             ) {
            totalFee = totalFee + e1.getFee();
            System.out.println(totalFee);
        }
        lblTotalFee.setText(String.valueOf(totalFee));
    }





    private void setTimeAndDate(){
        dateTime.setTimeAndData(lblDate,lblTime);
    }



    StudentCourseDTO studentCourseDTO  = new StudentCourseDTO();

    private StudentDTO getStudent(){


        if(Double.parseDouble(lblTotalFee.getText())!= 0){

            StudentDTO student =  new StudentDTO();
            student.setStudentFName(txtFirstName.getText());
            student.setStudentLName(txtLastName.getText());
            student.setAddress(txtAddress.getText());
            student.setIdNumber(txtIdNumber.getText());
            student.setGender(cmbGender.getValue());
            student.setTotalFee(Double.parseDouble(lblTotalFee.getText()));
            return student;
        }

        return null;


    }



    List<CourseDTO> courseDTOArrayList= new ArrayList<>();
    public void convertCartTM_to_courseDTO(){
        for (CartTM c1 : cartTMObservableList
             ) {
            courseDTOArrayList.add(new CourseDTO(c1.getCourseId(),c1.getCourseName(),c1.getCourseDuration(),c1.getFee()));
        }

    }

    public void saveOnAction(ActionEvent actionEvent) {
        StudentDTO student = getStudent();
        convertCartTM_to_courseDTO();

        boolean b = studentBo.studentAddCourse(student,courseDTOArrayList,lblDate.getText(),lblTime.getText());

        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"successFull saved").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"not successFull saved").show();
        }


    }

    public void backHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../views/DashBordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.studentA.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

}
