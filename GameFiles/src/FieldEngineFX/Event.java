package FieldEngineFX;

/**
 * Created by Ben on 9/25/2015.
 */
public interface Event {
    Action eventInvoker();

    void runEvent(Action incomingAction);
}
