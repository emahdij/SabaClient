package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    final static String source = "/home/" + "mehdi" + "/SabaAplicationSource/";
    final static String sourceuser = source + "Users/";
    final static String userMainCash = sourceuser + "Maincash/";
    final static String sourceuserProfiles = sourceuser + "Profiles";

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        new File(sourceuser).mkdir();
//        new File(source).mkdir();
//        File profiles = new File(sourceuserProfiles);
//        profiles.createNewFile();
//        File MainCash = new File(userMainCash);
//        MainCash.createNewFile();
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void ChangeScene(String SceneName) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(SceneName));
        stage.setScene(new Scene(root));

    }

    public static void main(String[] args) {
        launch(args);

    }
}
