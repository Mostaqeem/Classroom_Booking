package sample;

import java.io.Serializable;

public class Classroom implements Serializable {
  private String fullName;
  private String facInitial;
  private String numOfStudents;
  private String facultyID;
  private String roomNum;
  private String timeSlot;
  private String classDate;
  private String pathToPhoto;

  public Classroom(String fullName, String facInitial, String numOfStudents, String facultyID,
                   String roomNum, String timeSlot, String classDate, String pathToPhoto) {
    this.fullName = fullName;
    this.facInitial = facInitial;
    this.numOfStudents = numOfStudents;
    this.facultyID = facultyID;
    this.roomNum = roomNum;
    this.timeSlot = timeSlot;
    this.classDate = classDate;
    this.pathToPhoto = pathToPhoto;
  }

  public String getFullName() {
    return fullName;
  }

  public String getFacInitial() {
    return facInitial;
  }

  public String getNumOfStudents() {
    return numOfStudents;
  }

  public String getFacultyID() {
    return facultyID;
  }

  public String getRoomNum() {
    return roomNum;
  }

  public String getTimeSlot() {
    return timeSlot;
  }

  public String getClassDate() {
    return classDate;
  }

  public String getPathToPhoto() {
    return pathToPhoto;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setFacInitial(String facInitial) {
    this.facInitial = facInitial;
  }

  public void setNumOfStudents(String numOfStudents) {
    this.numOfStudents = numOfStudents;
  }

  public void setFacultyID(String facultyID) {
    this.facultyID = facultyID;
  }

  public void setRoomNum(String roomNum) {
    this.roomNum = roomNum;
  }

  public void setTimeSlot(String timeSlot) {
    this.timeSlot = timeSlot;
  }

  public void setClassDate(String classDate) {
    this.classDate = classDate;
  }

  public void setPathToPhoto(String pathToPhoto) {
    this.pathToPhoto = pathToPhoto;
  }

  @Override
  public String toString() {
    String str = "Classroom : \n" +
      "\nfullName='" + fullName + '\'' +
      "\n, facInitial='" + facInitial + '\'' +
      "\n, numOfStudents='" + numOfStudents + '\'' +
      "\n, facultyID='" + facultyID + '\'' +
      "\n, roomNum='" + roomNum + '\'' +
      "\n, timeSlot='" + timeSlot + '\'' +
      "\n, classDate='" + classDate + '\'' +
      "\n, pathToPhoto='" + pathToPhoto;

    return fullName + ", " + timeSlot + ", " + classDate;

  }
}
