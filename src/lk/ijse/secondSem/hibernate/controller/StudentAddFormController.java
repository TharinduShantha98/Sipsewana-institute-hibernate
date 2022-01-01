package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.StudentCourseBoImpl;
import lk.ijse.secondSem.hibernate.bo.custom.StudentBo;
import lk.ijse.secondSem.hibernate.bo.custom.StudentCourseBO;
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
    public JFXTextField txtStudentSearch;
    public JFXButton btnUpdateStudent;
    public ImageView nextPage;
    public JFXButton btnSave;




    private  CourseDTO courseDTO;

    CourseBo courseBo = new CourseBoImpl();
    StudentBo studentBo = new StudentBoImpl();
    StudentCourseBO studentCourseBO = new StudentCourseBoImpl();

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
    ObservableList<CartTM> addNewCourse = FXCollections.observableArrayList();
    public void AddCartOnAction(ActionEvent actionEvent) {
        CartTM cartTM = new CartTM(courseDTO.getProgramId(),
                courseDTO.getProgram(),courseDTO.getDuration(),courseDTO.getFee());

        int rowNumber = -1;

        for(int i=0; i < cartTMObservableList.size(); i++){
            if(cartTMObservableList.get(i).getCourseId().equals(cartTM.getCourseId())){
                rowNumber=i;

            }

        }

        if(rowNumber == -1){
            cartTMObservableList.add(cartTM);
            addNewCourse.add(cartTM);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"This student is currently " +
                    "registered for this course ");
            alert.show();
        }

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


    List<StudentCourseDTO> search;
    public void searchStudentOnAction(ActionEvent actionEvent) {

        search = studentCourseBO.search(txtStudentSearch.getText());
        StudentCourseDTO studentCourseDTO = search.get(0);
        StudentDTO student = studentCourseDTO.getStudent();
        txtFirstName.setText(student.getStudentFName());
        txtLastName.setText(student.getStudentLName());
        txtAddress.setText(student.getAddress());
        txtIdNumber.setText(student.getIdNumber());
        cmbGender.getSelectionModel().select(student.getGender());
        lblTotalFee.setText(String.valueOf(student.getTotalFee()));

        List<CartTM> cartTMS = new ArrayList<>();

            for(int i =0; i < search.size(); i++){
                cartTMS.add(new CartTM(search.get(i).getCourse().getProgramId(),
                        search.get(i).getCourse().getProgram(),
                        search.get(i).getCourse().getDuration(),
                        search.get(i).getCourse().getFee()));

            }

            cartTMObservableList.addAll(cartTMS);
            tblCart.setItems(cartTMObservableList);


    }

    public void updateStudentOnAction(ActionEvent actionEvent) {

        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (CartTM tm : addNewCourse
             ) {
            courseDTOList.add(new CourseDTO(tm.getCourseId(),
                    tm.getCourseName(),
                    tm.getCourseDuration(),
                    tm.getFee()));
        }

        StudentDTO student = search.get(0).getStudent();
        student.setTotalFee(Double.parseDouble(lblTotalFee.getText()));
        List<StudentCourseDTO> studentCourseDTO1 = new ArrayList<>();


        for (CourseDTO c1: courseDTOList
        ) {
            studentCourseDTO1.add(new StudentCourseDTO(student,c1,lblDate.getText(),lblTime.getText()));
        }

        boolean b = studentBo.studentAddNewCourse(student, studentCourseDTO1);

        Alert alert;
        if(b){
            alert = new Alert(Alert.AlertType.CONFIRMATION,"add new Course success");

        }else{
            alert = new Alert(Alert.AlertType.WARNING,"add new Course not success");


        }
        alert.show();




    }




    public void backHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../views/DashBordForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.studentA.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }



}
