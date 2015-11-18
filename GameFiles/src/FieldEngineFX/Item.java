package FieldEngineFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Created by Ben on 9/28/2015.
 */
public abstract class Item extends ImageView implements Event {
    protected String externalName;
    protected boolean sellable;
    protected int value;

    public Item(String graphicName, boolean sellable, int value, String externalName) {
        super(new Image(Launcher.pullResource("Database/Graphic_Icons/" + graphicName)));
        this.externalName = externalName;
        this.sellable = sellable;
        this.value = value;
    }

    @Override
    public Action eventInvoker() {
        return Action.USE;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }
}
