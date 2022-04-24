import javafx.scene.shape.Circle;

public class ViewableParticle extends Circle {

    private final Particle particle;

    public ViewableParticle(Particle particle) {
        this.particle = particle;
        setCenterX(particle.getPx());
        setCenterY(particle.getPy());
        setRadius(particle.getRadius());
        setFill(particle.getColor());
    }

    public void update(int delta) {
        setCenterX(particle.getPx());
        setCenterY(particle.getPy());
    }

}
