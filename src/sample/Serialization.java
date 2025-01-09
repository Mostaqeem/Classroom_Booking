package sample;

import java.io.*;
import java.util.ArrayList;

public class Serialization {

  public static void serializeClassroom(Classroom classroom) {
    File classroomSerialized = new File("Data.bin");
    try (
      FileOutputStream fileOutputStream = new FileOutputStream(classroomSerialized);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    ) {
      objectOutputStream.writeObject(classroom);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  public static void serialize(ArrayList<Classroom> classroomArrayList) {
    File classroomSerialized = new File("Data.bin");
    try (
      FileOutputStream fileOutputStream = new FileOutputStream(classroomSerialized);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    ) {
      objectOutputStream.writeObject(classroomArrayList);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  public static Classroom deserializeClassroom(String file_path) {
    File fileToRead = new File(file_path);
    Classroom classroomReadFromFile = null;

    try (
      FileInputStream fileInputStream = new FileInputStream(fileToRead);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
    ) {

      classroomReadFromFile = (Classroom) objectInputStream.readObject();

    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
    return classroomReadFromFile;
  }

  public static ArrayList<Classroom> deserialize(String file_path) {
    File fileToRead = new File(file_path);
    ArrayList<Classroom> classroomReadFromFile = null;

    try (
      FileInputStream fileInputStream = new FileInputStream(fileToRead);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
    ) {

      classroomReadFromFile = (ArrayList<Classroom>)objectInputStream.readObject();

    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
    return classroomReadFromFile;
  }
}
