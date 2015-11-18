package FieldEngineFX;

/**
 * Created by Ben on 9/25/2015.
 */
public abstract class FieldEvent extends FieldNode implements Event {
    private Action eventInvoker;
    private Amount replayAmount;
    private Position startingPosition;

    public FieldEvent(Position startingPosition, Action eventInvoker, Amount  replayAmount) {
        this.eventInvoker = eventInvoker;
        this.startingPosition = startingPosition;
        this.replayAmount = replayAmount;
    }

    public void finishInitializing() {
        super.moveToPoint(Launcher.getPointAtPosition(startingPosition));

        this.setFillHeight(Launcher.calcFillHeight());
        this.setFillWidth(Launcher.calcFillWidth());
        this.boundary = new Boundary(this);
    }

    public void runEvent(Action incomingAction) {
        if (replayAmount.isValid() && incomingAction.equals(eventInvoker)) {
            eventScript();

            replayAmount.decrement();
        }
    }

    @Override
    public Action eventInvoker() {
        return eventInvoker;
    }

    public abstract void eventScript();
}
