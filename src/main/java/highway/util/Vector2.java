package highway.util;

/**
 * 平面座標を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Vector2 {
    public static final Vector2 Zero = new Vector2(0, 0);

    public static final Vector2 Up = new Vector2(0, 1);
    public static final Vector2 Right = new Vector2(1, 0);
    public static final Vector2 Down = new Vector2(0, -1);
    public static final Vector2 Left = new Vector2(-1, 0);

    private final double x;
    private final double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * ベクトルのなす角を返します
     * @return
     */
    public double getTheta() {
        Vector2 other = Right;
        return Math.acos(dot(other) / (this.getMagnitude() * other.getMagnitude()));
    }

    /**
     * ベクトルの長さを返します
     * @return
     */
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 plus(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 minus(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 multiply(double scale) {
        return new Vector2(this.x * scale, this.y * scale);
    }

    public double dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    public Vector2 getRotated(double rotation)
    {
        rotation = -rotation / 180 * Math.PI;
        double cosθ = Math.cos(rotation);
        double sinθ = Math.sin(rotation);
        double x = this.x * cosθ - this.y * sinθ;
        double y = this.x * sinθ + this.y * cosθ;
        return new Vector2(x, y);
    }

    /**
     * このベクトルから指定した長さと角度へ進んだ新しいベクトルを返します
     * @param length 長さ
     * @param theta 角度
     * @return 進んだベクトル
     */
    public Vector2 getForword(double length, double theta) {
        double x = this.x + length * Math.cos(theta);
        double y = this.y + length * Math.sin(theta);
        return new Vector2(x, y);
    }
}
