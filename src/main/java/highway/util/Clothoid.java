package highway.util;


import java.util.Vector;

/**
 * クロソイド曲線を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Clothoid {

    public enum Type {
        LeftUp, RightUp, LeftDown, RightDown
    }

    private final double targetRadius;
    private final double targetLength;
    private final Type type;

    public Clothoid(double targetRadius, double targetLength) {
        this.targetRadius = targetRadius;
        this.targetLength = targetLength;
        this.type = Type.LeftUp;
        initialize();
    }

    public Clothoid(double targetRadius, double targetLength, Type type) {
        this.targetRadius = targetRadius;
        this.targetLength = targetLength;
        this.type = type;
        initialize();
    }

    public boolean hasNext() {
        if (isUpType()) {
            return l < targetLength;
        } else {
            return l > 0;
        }
    }

    private double l;
    private Vector2 origin;
    private Double rotation;

    private Vector2 last1Position;
    private Vector2 last2Position;

    private Vector2 internalNext() {
        final double A = Math.sqrt(targetRadius * targetLength);

        boolean hasNext = hasNext();

        head();

        if (!hasNext && !isUpType()) {
            Vector2 forward = last1Position.minus(last2Position).getNormalized();
            last2Position = last1Position;
            last1Position = last2Position.plus(forward.multiply(Math.abs(l)));
            return last1Position;
        }

        double r = A * A / l;
        double τ = l / (2 * r);

        double x = 1;
        double prefix = -1;
        for (int k = 2; k < 100; k += 2) {
            double delta = (1.0 / (MathUtility.fact(k) * (k * 2.0 + 1.0))) * Math.pow(τ, k);
            delta *= prefix;
            prefix *= -1;
            x += delta;
        }

        x *= A * Math.sqrt(2 * τ);

        double y = 1.0 / 3.0;
        prefix = -1;
        for (int k = 2; k < 100; k += 2) {
            double delta = (1.0 / (MathUtility.fact(k + 1.0) * ((k + 1.0) * 2.0 + 1.0))) * Math.pow(τ, k);
            delta *= prefix;
            prefix *= -1;
            y += delta;
        }

        y *= -A * τ * Math.sqrt(2 * τ);

        last2Position = last1Position;
        last1Position = new Vector2(x, y);

        return last1Position;
    }

    public Vector2 next() {
        Vector2 position = internalNext().minus(origin);
        Vector2 rotated = position.getRotated(rotation);

        if (shouldBeFlipOnY()) {
            return new Vector2(rotated.getX(), -rotated.getY());
        }

        return rotated;
    }

    public double getNextTheta() {
        next();
        double theta = last1Position.minus(last2Position).getTheta();
        if (type == Type.RightUp) {
            return -theta;
        } else if (type == Type.LeftDown) {
            return theta - rotation;
        } else if (type == Type.RightDown) {
            return -theta + rotation;
        } else {
            return theta;
        }
    }

    private boolean shouldBeFlipOnY() {
        return type == Type.LeftDown || type == Type.RightUp;
    }

    private boolean isUpType() {
        return type == Type.LeftUp || type == Type.RightUp;
    }

    private boolean isRightType() {
        return type == Type.RightUp || type == Type.RightDown;
    }

    private void initialize() {
        initializeL();
        Vector2 first = internalNext();
        Vector2 second = internalNext();
        rotation = new Double(second.minus(first).getTheta());
        origin = first;
        initializeL();
    }

    private void initializeL() {
        if (isUpType()) {
            l = 0;
        } else {
            l = targetLength;
        }
    }

    private void head() {
        if (isUpType()) {
            l += 5;
        } else {
            l -= 5;
        }
    }

}