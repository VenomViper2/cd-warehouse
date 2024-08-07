public class Cd {

    private int stock;
    private String artist;
    private String title;
    private final Charts charts;

    public Cd(String artist, String title, int stock, Charts charts) {
        this.stock = stock;
        this.artist = artist;
        this.title = title;
        this.charts = charts;
    }

    public void buy(Customer customer, Payment payment) {
        if (payment.paymentSuccess()) {
            customer.addToCollection(this);
            stock = stock -1;
            charts.notify(artist, title, 1);

        }
    }

    public int getStock() {
        return stock;
    }
}
