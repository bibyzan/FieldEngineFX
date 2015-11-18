package FieldEngineFX;

/**
 * 9/28/2015
 *
 * Made for use in objects that implement the Event interface.
 *
 * Amount objects simply contain the number of times a particular event
 * is supposed to occur. Each time it's used the decrement method is called.
 * The reason I felt this class was necessary was to help control between
 * Events that run once or twice, or events that always run.
 *
 * @author Ben Rasmussen
 */
public final class Amount {
    public static Amount INDEFINITE = new Amount(true);

    private int amount;
    private boolean indefinite;

    private Amount(boolean indefinite) {
        this.indefinite = indefinite;
        this.amount = 0;
    }

    public Amount(int amount) {
        this.amount = amount;
        this.indefinite = false;
    }

    public Amount(Amount amount) {
        this.amount = amount.getAmount();
        this.indefinite = amount.isIndefinite();
    }

    public boolean isValid() {
        return indefinite || amount > 0;
    }

    public void modifyAmount(int change) {
        amount += change;
    }

    public void increment() {
        amount++;
    }

    public void decrement() {
        amount--;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Amount) {
            Amount a = (Amount) o;

            return a.isIndefinite() && indefinite || a.getAmount() == amount;
        }

        return false;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int Amount) {
        this.amount = Amount;
    }

    public boolean isIndefinite() {
        return indefinite;
    }
}
