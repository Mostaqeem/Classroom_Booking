package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


public class Controller {
  private String photoPath = null;
  private ArrayList<Classroom> classRoomArrayList = null;
  private ObservableList<Classroom> classroomObservableList = null;
  private int indexSelectedFromListView;

  @FXML
  private ComboBox<String> roomNumComboBox;

  @FXML
  private ComboBox<String> timeSlotComboBox;

  @FXML
  private TextField fullNameTextField;

  @FXML
  private TextField facIdTextField;

  @FXML
  private TextField numOfStudentsTextField;

  @FXML
  private TextField facInitialTextField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button choosePhotoButton;

  @FXML
  private ImageView chosenPhoto;

  @FXML
  private Button saveButton;

  @FXML
  private Button clearButton;

  @FXML
  private ListView<Classroom> listView;

  @FXML
  private Button editSelectedButton;

  @FXML
  private Button veiwDetailsButton;

  private void resetUI() {
    this.fullNameTextField.setText("");
    this.facInitialTextField.setText("");
    this.numOfStudentsTextField.setText("");
    this.facIdTextField.setText("");
    this.roomNumComboBox.setValue(null);
    this.timeSlotComboBox.setValue(null);
    this.datePicker.setValue(null);
    this.photoPath = null;
    this.chosenPhoto.setImage(null);
  }

  private void updateProfileImageViewNode() {
    Image image = new Image("file://" + this.photoPath);
    this.chosenPhoto.setImage(image);
  }

  @FXML
  void handleChoosePhotoButtonClick(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.choosePhotoButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(primaryStage);

    if (selectedFile != null) {
      String selectedFilePath = selectedFile.toURI().getPath();
      this.photoPath = selectedFilePath;

      updateProfileImageViewNode();
    }
  }

  @FXML
  void handleClearButtonClick(ActionEvent event) {
    resetUI();
  }

  @FXML
  void handleEditSelectedButtonClick(ActionEvent event) {
    this.indexSelectedFromListView = this.listView.getSelectionModel().getSelectedIndex();
    if (this.indexSelectedFromListView != -1) {
      Classroom savedClassroom = this.listView.getItems().get(indexSelectedFromListView);
      String fullName = savedClassroom.getFullName();
      String facInitial = savedClassroom.getFacInitial();
      String numOfStudents = savedClassroom.getNumOfStudents();
      String facultyID = savedClassroom.getFacultyID();
      String roomNum = savedClassroom.getRoomNum();
      String timeSlot = savedClassroom.getTimeSlot();
      LocalDate classDate = LocalDate.parse(savedClassroom.getClassDate());
      this.photoPath = savedClassroom.getPathToPhoto();
      this.updateUIWithSavedClassroom(fullName, facInitial, numOfStudents, facultyID, roomNum, timeSlot,
        classDate);
    }
    System.out.println(this.indexSelectedFromListView);
  }

  public void updateUIWithSavedClassroom(String fullName, String facInitial, String numOfStudents,
                                         String facultyID, String roomNum, String timeSlot,
                                         LocalDate classDate) {
    this.fullNameTextField.setText(fullName);
    this.facInitialTextField.setText(facInitial);
    this.numOfStudentsTextField.setText(numOfStudents);
    this.facIdTextField.setText(facultyID);
    this.roomNumComboBox.setValue(roomNum);
    this.timeSlotComboBox.setValue(timeSlot);
    this.datePicker.setValue(classDate);
    updateProfileImageViewNode();

  }
  @FXML
  void handleSaveButtonClick(ActionEvent event) {
    String fullName = this.fullNameTextField.getText();
    String facInitial = this.facInitialTextField.getText();
    String numOfStudents = this.numOfStudentsTextField.getText();
    String facultyID = this.facIdTextField.getText();
    String roomNum = this.roomNumComboBox.getSelectionModel().getSelectedItem();
    String timeSlot = this.timeSlotComboBox.getSelectionModel().getSelectedItem();
    String classDate = this.datePicker.getValue().toString();
    String pathToPhoto = this.photoPath;

    try {
      boolean b = Validator.isValid(fullName, facInitial, facultyID, numOfStudents, roomNum, timeSlot, classDate,
        pathToPhoto);
      Classroom classroom = new Classroom(fullName, facInitial, numOfStudents, facultyID, roomNum,
        timeSlot, classDate, pathToPhoto);
      if (this.indexSelectedFromListView == -1) {
        this.classRoomArrayList.add(classroom);
        this.classroomObservableList.add(classroom);

      } else {
        this.classRoomArrayList.set(this.indexSelectedFromListView, classroom);
        this.classroomObservableList.set(this.indexSelectedFromListView, classroom);
        this.listView.refresh();
      }
      Serialization.serialize(this.classRoomArrayList);
    } catch (Exception e) {
      Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
      viewUtilities.showErrorMassageDialogueBox(e.getMessage(), primaryStage);
    }

    resetUI();
  }

  @FXML
  void handleTextViewClicked(MouseEvent event) {

  }

  @FXML
  void veiwDetailsButtonClicked(ActionEvent event) {
    this.indexSelectedFromListView = this.listView.getSelectionModel().getSelectedIndex();

    if (this.indexSelectedFromListView != -1) {
      Classroom classroom = this.listView.getItems().get(this.indexSelectedFromListView);
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailedView.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        DetailedViewController detailedViewController = fxmlLoader.getController();
        detailedViewController.transferClassroomObject(classroom);

        Scene detailViewScene = new Scene(root);
        Stage primaryStage = (Stage) this.veiwDetailsButton.getScene().getWindow();
        primaryStage.setScene(detailViewScene);
        primaryStage.setTitle("Reserved Classroom Detailed View for : " + classroom.getFullName());
        primaryStage.show();
      } catch (Exception e) {
        Stage primaryStage = (Stage) this.veiwDetailsButton.getScene().getWindow();
        viewUtilities.showErrorMassageDialogueBox(e.getMessage(), primaryStage);
      }
    }

    //.load(getClass().getResource("sample.fxml"));
  }

  @FXML
  public void initialize() {
    //populate room number comboBox
    ArrayList<String> roomNumOptions = new ArrayList<>();
    roomNumOptions.add("501A");
    roomNumOptions.add("501B");
    roomNumOptions.add("501C");
    roomNumOptions.add("502A");
    roomNumOptions.add("502B");
    roomNumOptions.add("503A");
    roomNumOptions.add("503B");
    roomNumOptions.add("401A");
    roomNumOptions.add("401B");
    roomNumOptions.add("402A");
    roomNumOptions.add("402B");
    roomNumOptions.add("301A");
    roomNumOptions.add("301B");
    roomNumOptions.add("302A");
    roomNumOptions.add("302C");
    roomNumOptions.add("201A");
    roomNumOptions.add("201B");
    ObservableList<String> roomNumObservableList = FXCollections.observableArrayList(roomNumOptions);
    this.roomNumComboBox.setItems(roomNumObservableList);

    //time slot combo box
    ArrayList<String> timeSlotOptions = new ArrayList<>();
    timeSlotOptions.add("8:00am to 9:30am");
    timeSlotOptions.add("9:40am to 11:10am");
    timeSlotOptions.add("11:20am to 12:50pm");
    timeSlotOptions.add("1:00pm to 2:30pm");
    timeSlotOptions.add("2:40pm to 4:10pm");
    timeSlotOptions.add("4:20pm to 5:50pm");
    ObservableList<String> timeSlotObservableList = FXCollections.observableArrayList(timeSlotOptions);
    this.timeSlotComboBox.setItems(timeSlotObservableList);

    //Arraylist.............
    this.classRoomArrayList = Serialization.deserialize("Data.bin");
    if (this.classRoomArrayList == null) {
      this.classRoomArrayList = new ArrayList<>();
    }
    this.classroomObservableList = FXCollections.observableList(classRoomArrayList);
    this.listView.setItems(classroomObservableList);


  }

}

