package highway.model;

import highway.config.RoadComponentConfig;
import highway.util.Point;
import highway.util.Vector2;

/**
 * 道路のモデルとなるクラス
 * モデルクラスは数種類しかなく、それらを元に１つの高速道路を構築する
 * Created by shohei.miyashita on 5/19/16.
 */
public class RoadComponent {

    public double length;
    public double gradient;

    public Point startPoint;
    public Point endPoint;

    private RoadComponent() {
    }

    public static RoadComponent createStartPoint(RoadComponentConfig config, RoadComponentConfig next) {
        RoadComponent component = new RoadComponent();
        component.length = config.length;
        component.startPoint = new Point(Vector2.Zero, 0, config.width);
        component.endPoint = component.startPoint.getLinearForwarded(next.length);
        return component;
    }

    public RoadComponent getNextComponent(RoadComponentConfig nextConfig) throws Exception {
        RoadComponent nextComponent = new RoadComponent();
        nextComponent.length = nextConfig.length;
        nextComponent.startPoint = this.endPoint;
        nextComponent.endPoint = nextComponent.getEndPointByShape(nextConfig);
        return nextComponent;
    }

    private Point getEndPointByShape(RoadComponentConfig nextConfig) throws Exception {
        // TODO 場合分けの返り値をちゃんとする
        switch (nextConfig.shape) {
            case "Line":
                return startPoint.getLinearForwarded(nextConfig.length);
            case "Circle":
                return startPoint.getLinearForwarded(nextConfig.length);
            case "Clothoid":
                return startPoint.getLinearForwarded(nextConfig.length);
            default:
                throw new Exception("");
        }
    }
}
