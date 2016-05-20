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


}
