package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public JFXTextField txtSId;



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

    }

    private void setTimeAndDate(){
        dateTime.setTimeAndData(lblDate,lblTime);
    }



    StudentCourseDTO studentCourseDTO  = new StudentCourseDTO();

    private StudentDTO getStudent(){
        StudentDTO student =  new StudentDTO();
        student.setStudentFName(txtFirstName.getText());
        student.setStudentLName(txtLastName.getText());
        student.setAddress(txtAddress.getText());
        student.setIdNumber(txtIdNumber.getText());
        student.setGender(cmbGender.getValue());

        return student;

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
}
