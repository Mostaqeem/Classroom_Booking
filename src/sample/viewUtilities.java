package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class viewUtilities {
  public static void showErrorMassageDialogueBox(String errorMassage,Stage parentStage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(viewUtilities.class.getResource("ErrorView.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      ErrorViewController errorViewController = fxmlLoader.getController();
      errorViewController.setErrorMassageLabel(errorMassage);

      Scene errorViewScene = new Scene(root);
      Stage errorStage = new Stage();
      errorStage.setScene(errorViewScene);
      errorStage.initOwner(parentStage);
      errorStage.initModality(Modality.APPLICATION_MODAL);

      errorStage.setTitle("Error");
      errorStage.showAndWait();

    }catch (Exception e){
      e.printStackTrace();
    }

  }
}
