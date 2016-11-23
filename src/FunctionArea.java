import acm.program.*;

public class FunctionArea extends ConsoleProgram {

    public void run() {
    }

    /**
     * The function whose area to calculate.
     *
     * @param x
     *            the x coordinate.
     * @return the value of f at the x coordinate.
     */
    public double f(double x) {
        // This is the function whose area we want to calculate. Hardcoding
        // it here is a bit unfortunate; we can improve the design once we
        // know more about classes and interfaces
        return Math.sin(x) + Math.cos(0.5 * x);
    }

    /**
     * Calculates the height of a rectangle at the given x coordinate and with
     * the given width. The height of the rectangle is the function value at its
     * left and right side that is nearer to the x axis. If the function value
     * is negative, the height is negative.
     *
     * @param x
     *            the rectangle's x coordinate (left boundary).
     * @param width
     *            the rectangle's width.
     * @return the rectangle's height, which may actually be negative.
     */
    public double minRectHeight(double x, double width) {
        double leftSide = f(x);
        double rightSide = f(x + width);
        if (Math.abs(leftSide) < Math.abs(rightSide)) {
            return leftSide;
        }
        return rightSide;
    }

    /**
     * Calculates the height of a rectangle at the given x coordinate and with
     * the given width. The height of the rectangle is the function value at its
     * left and right side that is further from the x axis. If the function value
     * is negative, the height is negative.
     *
     * @param x
     *            the rectangle's x coordinate (left boundary).
     * @param width
     *            the rectangle's width.
     * @return the rectangle's height, which may actually be negative.
     */
    public double maxRectHeight(double x, double width) {
        double leftSide = f(x);
        double rightSide = f(x + width);
        if (Math.abs(leftSide) > Math.abs(rightSide)) {
            return leftSide;
        }
        return rightSide;
    }

    /**
     * Approximates the area enclosed by the x axis, {@link #f(double)}, and two
     * vertical lines at {@code left} and {@code right}. The approximation
     * divides the x axis section into different parts of width
     * {@code rectWidth} (the rightmost part may have to be smaller to keep it
     * from extending beyond the right boundary). For each part, the function
     * computes the min and max rectangle and uses the min rectangle's area plus
     * half the difference of the two rectangle areas as the approximate area
     * for that part.
     *
     * @param left
     *            left boundary.
     * @param right
     *            right boundary.
     * @param rectWidth
     *            width of the rectangles used to approximate the area.
     * @return the approximate area. If the left boundary is right of the right
     *         boundary, the result is 0.
     */
    public double approxFunctionArea(double left, double right, double rectWidth) {
        double area = 0;

        // this would otherwise turn into a loop
        if (rectWidth == 0) {
            return area;
        }

        for (double x = left; x < right; x= x + rectWidth) {
            double actualRectWidth = rectWidth;
            // if the rect would be wider than allowed by right, shorten it accordingly
            if (x + rectWidth > right) {
                actualRectWidth = right - x;
            }
            area += ((minRectHeight(x, actualRectWidth) + maxRectHeight(x, actualRectWidth)) / 2) * actualRectWidth;
        }

        return area;
    }
}
