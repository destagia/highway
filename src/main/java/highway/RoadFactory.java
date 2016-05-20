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
        try {
            Road road = new Road(configs[0]);

            // 最初の１つは始点として特別な方法で生成される
            // 最後の１つは次のコンポーネントがないためそこが端点となる
            for (int i = 1; i < configs.length - 1; i++) {
                road.append(configs[i]);
            }

            return road;
        } catch (Exception e) {
            return null;
        }
    }
}
