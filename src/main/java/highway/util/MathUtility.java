package highway.util;

/**
 * 数学的計算を行う関数群
 * 定義される関数は全てstatic関数
 * Created by shohei.miyashita on 5/20/16.
 */
public class MathUtility {

    /**
     * 絶対値の計算
     * @param a
     * @return
     */
    public double abst(double a) {
        if (a < 0) a = -a;
        return a;
    }

    public static double fact(double value) {
        double count = value;
        for (int i = 2; i < count; i++) {
            value *= i;
        }
        return value;
    }

}
