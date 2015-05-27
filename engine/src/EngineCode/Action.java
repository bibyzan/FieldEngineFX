package EngineCode;

/**
 * Created by benra_000 on 5/15/2015.
 */
public final class Action {
    public static final Action
            TALK = new Action(0),
            ATTACK = new Action(1),
            DEFEND = new Action(2),
            INTERACT = new Action(3),
            IDLE = new Action(4),
            MOVING = new Action(5);

    private int action;

    private Action(int action) {
        this.action = action;
    }

    public Action(Action action) {
        this.action = action.getAction();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return true;
        else
        if (obj instanceof Action) {
            Action dir = (Action)obj;

            return action == dir.getAction();
        } else
            return false;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int Action) {
        this.action = Action;
    }
}
