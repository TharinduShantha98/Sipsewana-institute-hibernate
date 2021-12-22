package lk.ijse.secondSem.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.secondSem.hibernate.bo.custom.CourseBo;
import lk.ijse.secondSem.hibernate.bo.custom.Impl.CourseBoImpl;
import lk.ijse.secondSem.hibernate.dto.CourseDTO;

import java.util.List;


public class CourseFormController {
    public JFXTextField TxtProgramId;
    public JFXTextField TxtProgram;
    public JFXTextField TxtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtSearchProId;
    public ImageView updateTextFiled;
    public TableView<CourseDTO> tblCourse;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;

    public void  initialize(){

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        getAllCourse();



    }

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
        TxtDuration.setText(courseDTO.getDuration());
        txtFee.setText(String.valueOf(courseDTO.getFee()));

    }


    public void DeleteProgramOnAction(ActionEvent actionEvent) {
        CourseDTO courseDTO = new CourseDTO(TxtProgramId.getText(),TxtProgram.getText(),
                TxtDuration.getText(),Double.parseDouble(txtFee.getText()));
        boolean b = courseBo.deleteCourse(courseDTO);

        Alert alert;
        if(b){
            alert = new Alert(Alert.AlertType.CONFIRMATION, "successFul delete ");

        }else{
            alert = new Alert(Alert.AlertType.WARNING, "not successFul delete");
        }
        alert.show();
    }


    private void  getAllCourse(){
        List<CourseDTO> all = courseBo.getAll();
        ObservableList<CourseDTO> observableList = FXCollections.observableArrayList();
        observableList.addAll(all);

        tblCourse.setItems(observableList);

    }

    public void updateProgramOnAction(ActionEvent actionEvent) {
        CourseDTO courseDTO = new CourseDTO(TxtProgramId.getText(),TxtProgram.getText(),
                TxtDuration.getText(),Double.parseDouble(txtFee.getText()));

        boolean b = courseBo.updateCourse(courseDTO);

        Alert alert;
        if(b){
            alert = new Alert(Alert.AlertType.CONFIRMATION, " update successFul  ");

        }else{
            alert = new Alert(Alert.AlertType.WARNING, " update not successFul");
        }
        alert.show();

    }






    public void updateTextFiledOnAction(MouseEvent mouseEvent) {
        TxtProgramId.clear();
        TxtProgram.clear();
        TxtDuration.clear();
        txtFee.clear();
        txtSearchProId.clear();
        getAllCourse();

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
