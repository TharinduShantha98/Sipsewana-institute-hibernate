package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;




public class CourseFormController {
    public JFXTextField TxtProgramId;
    public JFXTextField TxtProgram;
    public JFXTextField TxtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtSearchProId;
    public ImageView updateTextFiled;



    private CourseBo courseBo = new CourseBoImpl();


    public void addProgramOnAction(ActionEvent actionEvent) {

        CourseDTO courseDTO = new CourseDTO(TxtProgramId.getText(),TxtProgram.getText(),
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

    public void searchCourseId_onAction(ActionEvent actionEvent) {
        CourseDTO courseDTO = courseBo.searchCourse(txtSearchProId.getText());
        TxtProgram.setText(courseDTO.getProgram());
        TxtProgramId.setText(courseDTO.getProgramId());
        TxtDuration.setText(courseDTO.getProgramId());
        txtFee.setText(String.valueOf(courseDTO.getFee()));

    }

    public void DeleteProgramOnAction(ActionEvent actionEvent) {



    }













    public void updateTextFiledOnAction(MouseEvent mouseEvent) {
        TxtProgramId.clear();
        TxtProgram.clear();
        TxtDuration.clear();
        txtFee.clear();
        txtSearchProId.clear();

    }

    public void mouseEnteredOnAction(MouseEvent mouseEvent) {
            ImageView icon = (ImageView) mouseEvent.getSource();
        if(mouseEvent.getSource() instanceof ImageView){
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);

        }

    }

    public void mouseExitedOnAction(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200),icon);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);
        scaleTransition.play();
        icon.setEffect(null);


    }
}
