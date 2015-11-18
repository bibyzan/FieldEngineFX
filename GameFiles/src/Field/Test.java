package Field;

import FieldEngineFX.*;
import FieldEngineFX.RPGMods.RPGPlayer;

/**
 * Created by Ben on 9/14/2015.
 */
public class Test extends Field {
    public Test() {
        super("center", new Position(4, 4));

        events.add(new TestEvent());
        events.add(new TestDamage());
    }

    private class TestEvent extends FieldEvent {
        public TestEvent() {
            super(new Position(1,4), Action.INTERACT,  new Amount(2));
        }

        @Override
        public void eventScript() {
            DialoguePane.showMessageDialogue("HAI");
        }
    }

    private class TestDamage extends FieldEvent {

        public TestDamage() {
            super(new Position(2,6), Action.MOVING, Amount.INDEFINITE);
        }

        @Override
        public void eventScript() {
            ((RPGPlayer)World.player).takeDamage(100, Direction.LEFT);
        }
    }
}
