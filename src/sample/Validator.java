package sample;

public class Validator {

  public static boolean isValid(String fullName, String facInitial, String facID, String numOFStudents,
                                String roomNum, String timeSlot, String classDate,
                                String photoPath) throws Exception {
    if (fullName == null) {
      throw new NullPointerException("The name is empty.");
    } else if (numOFStudents == null) {
      throw new Exception("You Didn't enter Number of students.");
    } else if (facInitial == null) {
      throw new NullPointerException("You Didn't enter faculty initial.");
    } else if (facID == null) {
      throw new Exception("You Didn't enter id.");
    } else if (roomNum == null) {
      throw new Exception("You Didn't select room number.");
    } else if (timeSlot == null) {
      throw new Exception("You Didn't select time slot.");
    } else if (classDate == null) {
      throw new Exception("You Didn't enter date.");
    } else if (photoPath == null) {
      throw new Exception("You Didn't choose photo.");
    } else {
      int i = 0;
      for (i = 0; i < fullName.length(); i++) {
        if (!((fullName.charAt(i) > 64 && fullName.charAt(i) < 91) || (fullName.charAt(i) > 96
          && fullName.charAt(i) < 123) || fullName.charAt(i) == ' ')) {
          throw new Exception("Name should have alphabets.");
        }
      }


      for (i = 0; i < numOFStudents.length(); i++) {
        if (!(numOFStudents.charAt(i) > 47 && numOFStudents.charAt(i) < 58)) {
          throw new Exception("Students number cannot have Alphabets or space.");
        }
      }

    }
    return true;

  }
}
