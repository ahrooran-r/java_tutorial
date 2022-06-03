package tutorial.learn.core._10_concurrency._6_concurrent_problems._3_sleeping_barber;

public class WaitingRoom {

    private final Chair[] chairs = new Chair[Constants.NUMBER_OF_CHAIRS.getValue()];

    public WaitingRoom() {
        for (int i = 0; i < Constants.NUMBER_OF_CHAIRS.getValue(); i++) {
            chairs[i] = new Chair(i);
        }
    }

    public boolean isVacant() {
        for (Chair chair : chairs) {
            if (chair.isVacant()) return true;
        }
        return false;
    }

    public boolean isFull() {
        return !isVacant();
    }

    public boolean takeChair(Customer customer) {
        for (Chair chair : chairs) {
            if (chair.isVacant()) chair.occupy(customer);
            return true;
        }
        return false;
    }
}
