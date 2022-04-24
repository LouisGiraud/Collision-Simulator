import Math2.Vector;
import javafx.scene.paint.Paint;

public class Particle implements Updatable {
    private double px, py, vx, vy, ax, ay;
    private final double radius;
    private final Paint color;
    public int isOff;

    public final static int OFF_CONSTANT = 5;

    public Particle(double px, double py, double vx, double vy, double ax, double ay,
                    double radius, Paint color) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = radius;
        this.color = color;
    }

    public Particle(double px, double py, double vx, double vy, Paint color) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        ax = 0;
        ay = 0;
        radius = 30;
    }

    public Particle(double px, double py, double vx, double vy, Paint color, int radius) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        ax = 0;
        ay = 0;
        this.radius = radius;
    }


    public Particle(double px, double py, double vx, double vy) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        ax = 0;
        ay = 0;
        radius = 30;
        color = Paint.valueOf("WHITE");
    }

    public Particle() {
        px = 250;
        py = 250;
        vx = 70;
        vy = -40;
        ax = 0;
        ay = 0;
        radius = 50;
        color = Paint.valueOf("WHITE");
    }

    @Override
    public void update(int d) {
        double delta = d / 1000.0; //delta is in s, d in ms
        px = delta * delta * ax / 2 + delta * vx + px;
        py = delta * delta * ay / 2 + delta * vy + py;

        vx = delta * ax + vx;
        vy = delta * ay + vy;

        if(isOff > 0) isOff -= 1;

    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public double getRadius() {
        return radius;
    }

    public Paint getColor() {
        return color;
    }

    public Vector getV() {return new Vector(vx, vy);}
    public Vector getP() {return new Vector(px, py);}

    private void setVy(double vy) {this.vy = vy;};
    private void setVx(double vx) {this.vx = vx;};


    public void collideWithFrame(Frame frame) {
        if ( isOff <= 0 && ( px - radius <= frame.getLeft()
        || px + radius >= frame.getRight() ) ) {
            vx *= -1;
            isOff = OFF_CONSTANT;
        }
        if ( isOff <= 0 && ( py - radius <= frame.getTop()
        || py + radius >= frame.getBottom() ) ) {
            vy *= -1;
            isOff = OFF_CONSTANT;
        }
    }

    public void collideWithParticle(Particle p) {
        if(shouldCollideWith(p) && isOff <= 0 && p.isOff <= 0) {
            double r1 = Math.pow(radius, 2);
            double r2 = Math.pow(p.getRadius(), 2);

            Vector v1 = new Vector(vx, vy);
            Vector v2 = p.getV();

            Vector p1 = new Vector(px, py);
            Vector p2 = p.getP();

            Vector p1mp2 = Vector.minus(p1, p2);
            Vector p2mp1 = Vector.minus(p2, p1);

            Vector v1mv2 = Vector.minus(v1, v2);
            Vector v2mv1 = Vector.minus(v2, v1);

            Vector newV1 = Vector.minus(v1,
                    p1mp2.multipliedByScalar(
                        ( (2 * r2) / (r1 + r2) ) *
                        ( Vector.scalarProduct(v1mv2, p1mp2) / p1mp2.squaredNorm() ) )
                    );

            Vector newV2 = Vector.minus(v2,
                    p2mp1.multipliedByScalar(
                        ( (2 * r1) / (r1 + r2) ) *
                        ( Vector.scalarProduct(v2mv1, p2mp1) / p2mp1.squaredNorm() ) )
            );

            vx = newV1.X();
            vy = newV1.Y();

            p.setVx(newV2.X());
            p.setVy(newV2.Y());

            p.isOff = OFF_CONSTANT;
            isOff = OFF_CONSTANT;
        }
    }

    private boolean shouldCollideWith(Particle p) {
        double squaredDistance =
                Math.pow((px - p.getPx()), 2) + Math.pow((py - p.getPy()), 2);
        double minDistance = Math.pow(radius + p.getRadius(), 2);
        return squaredDistance <= minDistance;
    }

}
