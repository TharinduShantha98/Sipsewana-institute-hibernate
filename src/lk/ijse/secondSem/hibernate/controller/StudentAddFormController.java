package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.dao.Custom.CourseDAO;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;

import java.util.ArrayList;

public class StudentAddFormController {


    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtAddress;
    public JFXComboBox<String> cmbCourseId;
    public JFXTextField txtIdNumber;
    public JFXComboBox<String>cmbGender;

    CourseBo courseBo = new CourseBoImpl();


    public void initialize(){
        setCmbCourseId();
        setCmbGender();

    }


    public void AddCartOnAction(ActionEvent actionEvent) {



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
}
