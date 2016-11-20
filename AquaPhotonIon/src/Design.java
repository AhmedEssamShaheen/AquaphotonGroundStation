import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Design extends Application {


    public static void launch() {
        Application.launch(Design.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Thread.sleep(100);
        primaryStage.setTitle("AquaPhoton Team");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "Images/Aqua.jpg" )));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
