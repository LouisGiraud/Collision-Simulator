package Math2;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double scalarProduct(Vector v1, Vector v2) {
        return v1.X() * v2.X() + v1.Y() * v2.Y();
    }

    public static Vector minus(Vector v1, Vector v2) {
        return new Vector(v1.X() - v2.X(), v1.Y() - v2.Y());
    }

    public Vector multipliedByScalar(double s) {
        return new Vector(x * s, y * s);
    }

    public Vector dividedByScalar(double s) {
        return new Vector(x / s, y / s);
    }

    public double norm() {
        return Math.sqrt(x*x + y*y);
    }

    public double squaredNorm() {
        return norm() * norm();
    }
}
