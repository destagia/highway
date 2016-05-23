package highway.util;


/**
 * クロソイド曲線を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Clothoid {

    public enum Type {
        LeftUp, RightUp, LeftDown, RightDown
    }

    private final double ΔLValue = 5.0;
    private final double ΔL;

    private final double CLOTHOID_PARAMETER;
    private final double TARGET_RADIUS;
    private final double TARGET_LENGTH;

    private final Type TYPE;

    public Clothoid(double TARGET_RADIUS, double TARGET_LENGTH) {
        this(TARGET_RADIUS, TARGET_LENGTH, Type.LeftUp);
    }

    public Clothoid(double targetRadius, double targetLength, Type type) {
        TARGET_RADIUS = targetRadius;
        TARGET_LENGTH = targetLength;
        TYPE = type;
        CLOTHOID_PARAMETER = Math.sqrt(targetRadius * targetLength);

        if (isUpType()) {
            ΔL = ΔLValue;
        } else {
            ΔL = -ΔLValue;
        }

        initializeL();

        Vector2 first = calculateNext();
        Vector2 second = calculateNext();
        rotation = new Double(second.minus(first).getTheta());
        origin = first;
        last1Position = origin;

        initializeL();
    }

    private void initializeL() {
        if (isUpType()) {
            l = 0;
        } else {
            l = TARGET_LENGTH;
        }
    }

    private double l;
    private Vector2 origin;
    private Double rotation;

    private Vector2 last1Position;
    private Vector2 last2Position;

    public Vector2 next() {
        Vector2 position = calculateNext().minus(origin);
        Vector2 rotated = position.getRotated(rotation);

        if (shouldFlipY()) {
            return new Vector2(rotated.getX(), -rotated.getY());
        }

        return rotated;
    }

    private Vector2 calculateNext() {
        boolean hasNext = hasNext();

        l = l + ΔL;

        if (!hasNext && !isUpType()) {
            Vector2 forward = last1Position.minus(last2Position).getNormalized();
            last2Position = last1Position;
            last1Position = last2Position.plus(forward.multiply(Math.abs(l)));
            return last1Position;
        }

        double x = 0, y = 0;

        if (l > 0) {
            double r = CLOTHOID_PARAMETER * CLOTHOID_PARAMETER / l;
            double τ = l / (2 * r);
            double prefix;

            x = 1;
            prefix = -1;
            for (int k = 2; k < 100; k += 2) {
                double delta = (1.0 / (MathUtility.fact(k) * (k * 2.0 + 1.0))) * Math.pow(τ, k);
                delta *= prefix;
                prefix *= -1;
                x += delta;
            }
            x *= CLOTHOID_PARAMETER * Math.sqrt(2 * τ);

            y = 1.0 / 3.0;
            prefix = -1;
            for (int k = 2; k < 100; k += 2) {
                double delta = (1.0 / (MathUtility.fact(k + 1.0) * ((k + 1.0) * 2.0 + 1.0))) * Math.pow(τ, k);
                delta *= prefix;
                prefix *= -1;
                y += delta;
            }
            y *= -CLOTHOID_PARAMETER * τ * Math.sqrt(2 * τ);
        }

        last2Position = last1Position;
        last1Position = new Vector2(x, y);

        return last1Position;
    }

    public boolean hasNext() {
        if (isUpType()) {
            return l < TARGET_LENGTH;
        } else {
            return l > 0;
        }
    }

    public double getNextTheta() {
        next();
        double theta = last1Position.minus(last2Position).getTheta();
        if (TYPE == Type.RightUp) {
            return -theta;
        } else if (TYPE == Type.LeftDown) {
            return theta - rotation;
        } else if (TYPE == Type.RightDown) {
            return -theta + rotation;
        } else {
            return theta;
        }
    }

    private boolean shouldFlipY() { return TYPE == Type.LeftDown || TYPE == Type.RightUp; }

    private boolean isUpType() { return TYPE == Type.LeftUp || TYPE == Type.RightUp; }
}