package lk.ijse.secondSem.hibernate.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.secondSem.hibernate.util.DateTime;

import java.io.IOException;


public class DashBordFormController {
    public Label lblTime;
    public Label lblDate;
    public Label lblTittle;
    public Label lblDescription;
    public ImageView studentReg;
    public ImageView studentCourse;
    public ImageView courses;
    public AnchorPane anchorPane;


    public void initialize(){
            datAndTime();

    }

    public  void datAndTime(){
        new DateTime().setTimeAndData(lblDate,lblTime);


    }

    public void mouseEnteredOnAction(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
                ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()){
                case "studentReg":
                    lblTittle.setText("student registration");
                    lblDescription.setText("click here if you want student registration");
                    break;
                case "studentCourse":
                    lblTittle.setText("student course detail");
                    lblDescription.setText("click here if you want student course detail");
                    break;
                case "courses":
                    lblTittle.setText("courses");
                    lblDescription.setText("click here if you want courses");
                    break;


            }

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
          if(mouseEvent.getSource() instanceof  ImageView){
              ImageView icon = (ImageView) mouseEvent.getSource();

              ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200),icon);
              scaleTransition.setToY(1);
              scaleTransition.setToX(1);
              scaleTransition.play();

              icon.setEffect(null);
              lblTittle.setText("WELCOME");
              lblDescription.setText("Please select one of above main operations to proceed");
          }
    }





    public void mouseClickedOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;
            switch (icon.getId()){
                case "studentReg":
                   root = FXMLLoader.load(this.getClass().getResource("../views/StudentAddForm.fxml"));
                   break;
                case "studentCourse":
                    root= FXMLLoader.load(this.getClass().getResource("../views/StudentCourseDetailForm.fxml"));
                    break;
                case "courses":
                    root = FXMLLoader.load(this.getClass().getResource("../views/CourseForm.fxml"));
                    break;
            }

            if(root != null){
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) this.anchorPane.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
            }


        }




    }





}
