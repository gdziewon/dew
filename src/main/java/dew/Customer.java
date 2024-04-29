package dew;

public class Customer {
    int funds;
    boolean regular;

    public Customer(int funds, boolean regular) {
        this.funds = funds;
        this.regular = regular;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }
}
