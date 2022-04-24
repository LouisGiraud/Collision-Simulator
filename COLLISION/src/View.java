import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;


public class View extends Application {

    private final static int FPS = 40;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupStage(primaryStage);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

    private void setupStage(Stage primaryStage) {
        int delta = 1000 / FPS;

        MainScene mainScene = new MainScene(primaryStage);

        //---------------------------Animation--------------------------------//
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainScene.updateScene(delta);
            }

        }, 0, delta);
    }



}
