package FieldEngineFX;

public class KeyControl {
    private String letter;
    private boolean pressed;
    private Action action;
    private Direction direction;
    private CommandEvent event;

    public KeyControl(String letter) {
        this.letter = letter;
        this.pressed = false;
    }

    public KeyControl(String letter, Action action) {
        this.letter = letter;
        this.action = action;
        this.pressed = false;
    }

    public KeyControl(String letter, Direction direction) {
        this.letter = letter;
        this.direction = direction;
        this.pressed = false;
    }

    public KeyControl(String letter, CommandEvent event) {
        this.letter = letter;
        this.event = event;
        this.pressed = false;
    }
    
    public void runEvent() {
        event.run();
    }

    public boolean hasEvent() {
        return event != null;
    }

    public CommandEvent getEvent() {
        return event;
    }

    public void setEvent(CommandEvent event) {
        this.event = event;
    }

    public boolean hasAction() {
        return action != null;
    }

    public boolean hasDirection() {
        return direction != null;
    }

    public String getLetter() {
        return letter;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}