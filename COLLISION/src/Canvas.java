import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.List;


public class Canvas implements Updatable {

    private final int width, height;
    private final Paint color;
    private final String title;
    private final Frame frame;
    private final List<Particle> particles;



    /**
     * General constructor
     * @param width (int)
     * @param height (int)
     * @param color (Paint)
     * @param title (String)
     * @param frame (Frame)
     * @param particles (List<Particle>)
     */
    public Canvas(int width, int height, Paint color, String title,
                  Frame frame, List<Particle> particles) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.title = title;
        this.frame = frame;
        this.particles = List.copyOf(particles);
    }

    /**
     * Constructor for quick canvas generation with default parameters
     */
    public Canvas() {
        width = 500;
        height = 500;
        color = Paint.valueOf(Color.web("0x08233B").toString()); //dunno how to do it differently
        title = "Collision Simulator";
        frame = new Frame(50, 450, 50, 450,
                Paint.valueOf("RED"), 5);
        particles = new ArrayList<>();
        particles.add(new Particle());
        particles.add(new Particle(300, 100, -50, 80, Paint.valueOf("GREEN"), 20));
        particles.add(new Particle(220, 410, -30, -15, Paint.valueOf("PINK"), 15));
        particles.add(new Particle(100, 150, 60, 20, Paint.valueOf("PURPLE"), 12));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Paint getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public Frame getFrame() {
        return frame;
    }

    public Particle getParticle(int i) {return particles.get(i);}

    public List<Particle> getParticles() {
        return particles;
    }

    //OVERRIDE METHODS

   @Override
    public void update(int delta) {
        handleCollision();
        for(Particle p : particles) {
            p.update(delta);

            for(Particle p2 : particles) {
                if(!p.equals(p2)) {
                    p.collideWithParticle(p2);
                }
            }
        }
    }

    private void handleCollision() {
        for(Particle p : particles) {
            p.collideWithFrame(frame);
        }
    }




}
