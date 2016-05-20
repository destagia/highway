package highway.model;

import highway.config.RoadComponentConfig;

import java.util.List;

/**
 * RoadComponentを組み合わせた道路全体を表すモデル
 * Created by shohei.miyashita on 5/20/16.
 */
public class Road {

    private RoadComponent firstComponent;
    private RoadComponent lastComponent;

    public Road(RoadComponentConfig startPointConfig) {
        lastComponent = firstComponent;
    }

    public void append(RoadComponentConfig config) {
        lastComponent.setNextByConfig(config);
    }
}
