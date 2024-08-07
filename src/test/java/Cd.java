public class Cd {

    private int stock;
    private String artist;
    private String title;
    private final Charts charts;
    private double price;
    private final PriceChecker priceChecker;

    public Cd(String artist, String title, int stock, Charts charts, double price, PriceChecker priceChecker) {
        this.stock = stock;
        this.artist = artist;
        this.title = title;
        this.charts = charts;
        this.price = price;
        this.priceChecker = priceChecker;
    }

    public void buy(Customer customer, Payment payment) {
        int chartPosition = charts.getChartPosition();
        if (chartPosition <= 100) {
            double lowestCompetitorPrice = priceChecker.getLowestCompetitorPrice();
            if(price >= (lowestCompetitorPrice - 1)) {
                price = lowestCompetitorPrice - 1;
            }
        }
        if (payment.paymentSuccess()) {
            customer.addToCollection(this);
            stock = stock -1;
            charts.notify(artist, title, 1);

        }
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
}
