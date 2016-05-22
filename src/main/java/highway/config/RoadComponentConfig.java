package highway.config;

/**
 * 1. 直線の道
 * shape = "Line"
 * required: length
 *
 * 2. クロソイド曲線
 * shape = "Clothoid"
 * required:
 *   circleRadius  (目標曲率半径)
 *   length        (目標曲率半径に到達するまでの距離)
 *   turn          (曲がる向き)
 *
 * 3. 曲線
 * shape = "Circle"
 * required:
 *   circleRadius (円の半径)
 *   length       (円周の距離)
 *   turn          (曲がる向き)
 *
 * Created by shohei.miyashita on 5/20/16.
 */
public class RoadComponentConfig {
    public String type;
    public String shape;
    public double width;

    public double length;
    public double circleRadius;
    public String turn;
}
