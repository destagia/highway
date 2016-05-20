package highway.model;

import highway.config.RoadComponentConfig;
import highway.util.Clothoid;
import highway.util.Fragment;
import highway.util.MathUtility;
import highway.util.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shohei.miyashita on 5/19/16.
 */
public class RoadComponent {

    private RoadComponentConfig config;

    private RoadComponent(Fragment prevComponentTerminal) {
        originFragment = prevComponentTerminal;
        terminalFragment = prevComponentTerminal;
    }

    private Fragment originFragment;
    private Fragment terminalFragment;

    public Vector2 getOrigin() {
        return originFragment.position;
    }

    private RoadComponent next;

    public void setNextByConfig(RoadComponentConfig config) throws RuntimeException {
        next = new RoadComponent(terminalFragment);
        next.config = config;
        if (config.shape.equals("Line")) {
            next.CreateLine();
        } else if (config.shape.equals("Clothoid")) {
            next.CreateClothoid();
        } else {
            throw new RuntimeException("Unknown shape : " + config.shape);
        }
    }

    private void CreateClothoid() {
        Clothoid clothoid = new Clothoid(config.length, config.circleRadius);
        for (Vector2 position = clothoid.next(); clothoid.hasNext();) {
            terminalFragment.setNext(new Fragment(originFragment.position.add(position)));
            terminalFragment = terminalFragment.getNext();
        }
    }

    private void CreateLine() {
        double theta = originFragment.getPrev() == null ? 0.0 : originFragment.getPrev().getForwardToNext().getTheta();
        Fragment fragment = new Fragment(getOrigin().getForword(config.length, theta));
        terminalFragment.setNext(fragment);
    }
}
