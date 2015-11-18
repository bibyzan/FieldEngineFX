package FieldEngineFX;

/**
 * 9/4/2015
 * This class is for the purpose of distinguishing between
 * what actions certain field nodes are doing on the screen at
 * a particular time. Comes in handy when it comes to interaction
 * between the player and certain parts of the field.
 *
 * The public statics are the only parts of this class intended to be used.
 *
 * @author Ben Rasmussen
 */
public final class Action {
    public static final Action
            TALK = new Action(0),
            ATTACK = new Action(1),
            DEFEND = new Action(2),
            INTERACT = new Action(3),
            IDLE = new Action(4),
            RUN_ON_STARTUP = new Action(5),
            MOVING = new Action(6),
            USE = new Action(7);

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
