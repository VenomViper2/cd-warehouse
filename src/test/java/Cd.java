public class Cd {
    private int stock;

    public Cd(String artist, String title, int stock) {
        this.stock = stock;

    }

    public void buy(Customer customer, Payment payment) {
        if (payment.paymentSuccess()) {
            customer.addToCollection(this);
            stock = stock -1;
        }
    }

    public int getStock() {
        return stock;
    }
}
