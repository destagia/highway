package highway;

import highway.config.RoadComponentConfig;
import highway.model.Road;
import highway.model.RoadComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * RoadComponentConfigからRoadComponentを作って
 * さらに、それらを組み合わせてRoadを作ります
 * Created by shohei.miyashita on 5/20/16.
 */
public class RoadFactory {

    public Road Construct(RoadComponentConfig[] configs) {
        List<RoadComponent> components = new ArrayList<RoadComponent>();
        try {
            RoadComponent prevRoad = RoadComponent.createStartPoint(configs[0], configs[1]);

            // 最初の１つは始点として特別な方法で生成される
            // 最後の１つは次のコンポーネントがないためそこが端点となる
            for (int i = 1; i < configs.length - 1; i++) {
                RoadComponentConfig current = configs[i];
                RoadComponent component = prevRoad.getNextComponent(current);
                components.add(component);
            }

            return new Road(components);
        } catch (Exception e) {
            return null;
        }
    }
}
