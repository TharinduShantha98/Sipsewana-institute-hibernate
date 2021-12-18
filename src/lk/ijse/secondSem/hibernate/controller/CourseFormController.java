package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;

public class CourseFormController {
    public JFXTextField TxtProgramId;
    public JFXTextField TxtProgram;
    public JFXTextField TxtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtSearchProId;


    private CourseBo courseBo = new CourseBoImpl();


    public void addProgramOnAction(ActionEvent actionEvent) {

        CourseDTO courseDTO = new CourseDTO(txtSearchProId.getText(),TxtProgram.getText(),
                TxtDuration.getText(),Double.parseDouble(txtFee.getText()));

        boolean addCourse = courseBo.addCourse(courseDTO);

        Alert alert;
        if(addCourse){
            alert = new Alert(Alert.AlertType.CONFIRMATION, "successFul add ");

        }else{
            alert = new Alert(Alert.AlertType.WARNING, "not successFul add");
        }
        alert.show();


    }
}
