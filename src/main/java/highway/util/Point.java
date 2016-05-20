package highway.util;

/**
 * 道路の始点や終点を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Point {

    private Vector2 origin;
    private double theta;
    private double width;

    public Vector2 getOrigin() {
        return origin;
    }

    public double getForword() {
        return theta;
    }

    public double getWidth () {
        return width;
    }

    public Point(Vector2 origin, double theta, double width) {
        this.origin = origin;
        this.theta = theta;
        this.width = width;
    }

    public Point getLinearForwarded(double length) {
        return new Point(origin.getForword(length, theta), theta, width);
    }

    public Point getClothiodForwarded(Clothoid clothoid, double length) {
        double x = clothoid.getClothoidX(origin.getX(), theta, length);
        double y = clothoid.getClothoidY(origin.getY(), theta, length);
        double theta = clothoid.getClothoidTheta(this.theta, length);
        return new Point(new Vector2(x, y), theta, width);
    }

    public Point getCircleForwared(double R, double length) {
        double x = calcCircleX(origin.getX(), theta, length, R);
        double y = calcCircleX(origin.getY(), theta, length, R);
        double theta = calcCircleTheta(this.theta, length, R);
        return new Point(new Vector2(x, y), theta, width);
    }

    public double calcCircleTheta(double startvector, double length, double R) {
        return startvector + length / R;
    }

    private double calcCircleX(double startx, double startvector, double length, double R) {
        double x, y;
        x = R * Math.sin(length / R);
        y = R - R * Math.cos(length / R);
        return Math.cos(startvector) * x - Math.sin(startvector) * y + startx;
    }

    private double calcCircleY(double starty, double startvector, double length, double R) {
        double x, y;
        x = R * Math.sin(length / R);
        y = R - R * Math.cos(length / R);
        return Math.sin(startvector) * x + Math.cos(startvector) * y + starty;
    }
}
