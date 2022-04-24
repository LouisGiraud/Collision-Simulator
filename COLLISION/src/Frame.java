import javafx.scene.paint.Paint;

public final class Frame {
    private final int left, right, top, bottom, strokeWidth;
    private final Paint color;


    public Frame(int left, int right, int top, int bottom, Paint color, int strokeWidth) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public Paint getColor() {
        return color;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }
}
