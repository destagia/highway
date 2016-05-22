package highway.util;


/**
 * クロソイド曲線を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Clothoid {

    private final double targetRadius;
    private final double targetLength;

    public Clothoid(double targetRadius, double targetLength) {
        this.targetRadius = targetRadius;
        this.targetLength = targetLength;
    }

    public boolean hasNext() {
        return l < targetLength;
    }

    private double l;

    public Vector2 next() {

        final double A = Math.sqrt(targetRadius * targetLength);

        if (l <= targetLength) {
            l += 0.1;

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

            return new Vector2(x, y);
        }

        return null;
    }

}
