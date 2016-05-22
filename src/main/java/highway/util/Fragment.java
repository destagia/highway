package highway.util;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class Fragment {
    public final Vector2 position;
    private Fragment next;
    private Fragment prev;
    private Vector2 forwardToNext;

    public Fragment getNext() {
        return next;
    }

    public Fragment getPrev() {
        return prev;
    }

    public Vector2 getForwardToNext() {
        return forwardToNext;
    }

    public void setNext(Fragment next) {
        this.next = next;
        this.next.prev = this;
        forwardToNext = next.position.minus(position);
    }

    public Fragment(Vector2 position) {
        this.position = position;
    }
}
