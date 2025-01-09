package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DetailedViewController {

  @FXML
  private ImageView image;

  @FXML
  private Label fullName;

  @FXML
  private Label facInitial;

  @FXML
  private Label studentNum;

  @FXML
  private Label facID;

  @FXML
  private Label roomNum;

  @FXML
  private Label timeSlot;

  @FXML
  private Label date;

  @FXML
  private Button closeButton;

  @FXML
  void handleCloseButton(ActionEvent event) {
    resetUI();
    this.switchToControllerView();

  }

  private void switchToControllerView() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
      Pane root = (Pane) fxmlLoader.load();

      Scene controllerScene = new Scene(root);
      Stage primaryStage = (Stage) this.closeButton.getScene().getWindow();
      primaryStage.setScene(controllerScene);
      primaryStage.setTitle("Reserve Classroom");
      primaryStage.show();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  private void resetUI() {
    this.fullName.setText("");
    this.facInitial.setText("");
    this.studentNum.setText("");
    this.facID.setText("");
    this.roomNum.setText("");
    this.timeSlot.setText("");
    this.date.setText("");
    this.image.setImage(null);
  }

  public void transferClassroomObject(Classroom classroom) {
    String photoPath = "file://" + classroom.getPathToPhoto();
    String fullName = classroom.getFullName();
    String facInitial = classroom.getFacInitial();
    String studentNum = classroom.getNumOfStudents();
    String facID = classroom.getFacultyID();
    String roomNum = classroom.getRoomNum();
    String timeSlot = classroom.getTimeSlot();
    String date = classroom.getClassDate();

    this.fullName.setText(fullName);
    this.facInitial.setText(facInitial);
    this.studentNum.setText(studentNum);
    this.facID.setText(facID);
    this.roomNum.setText(roomNum);
    this.timeSlot.setText(timeSlot);
    this.date.setText(date);
    this.image.setImage(new Image(photoPath));


  }

}
