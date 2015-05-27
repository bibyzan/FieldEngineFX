package EngineCode;

/**
 * Created by benra_000 on 5/17/2015.
 */
public abstract class FieldEvent {
    protected Field parentField;
    protected FieldNode parentNode;

    public FieldEvent(Field parentField, FieldNode parentNode) {
        this.parentField = parentField;
        this.parentNode = parentNode;
    }

    public abstract void runEvent();
}
