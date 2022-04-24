import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainScene {

    private final Scene scene;
    private final Canvas canvas;
    private final List<ViewableParticle> particles;

    public MainScene(Stage primaryStage) {

        canvas = new Canvas();
        //scene creation

        Rectangle frame = frame();

        //Particles initialisation
        particles = new ArrayList<>();
        for(Particle p : canvas.getParticles()) {
            ViewableParticle temp = new ViewableParticle(p);
            particles.add(temp);
        }

        //Scene initialisation
        Group root = new Group();
        root.getChildren().add(frame);
        for(ViewableParticle p : particles) {
            root.getChildren().add(p);
        }

        scene = new Scene(root, canvas.getHeight(), canvas.getWidth());

        scene.setFill(canvas.getColor());
        primaryStage.setTitle(canvas.getTitle());
        primaryStage.setScene(scene);
    };



    public Scene getScene() {
        return scene;
    }

    public void updateScene(int delta) {
        canvas.update(delta);
        for(ViewableParticle p : particles) {
            p.update(delta);
        }
    }



    private Rectangle frame() {
        Frame tempFrame = canvas.getFrame();
        int strokeWidth = tempFrame.getStrokeWidth();
        double width = tempFrame.getRight() - tempFrame.getLeft()
                + 2 * strokeWidth;
        double height = tempFrame.getBottom() - tempFrame.getTop()
                + 2 * strokeWidth;
        Rectangle frame = new Rectangle(tempFrame.getLeft() - strokeWidth
                , tempFrame.getTop() - strokeWidth,
                width, height);

        frame.setFill(Color.TRANSPARENT);
        frame.setStroke(tempFrame.getColor());
        frame.setStrokeWidth(tempFrame.getStrokeWidth());

        return frame;
    }

}
