public class Warehouse {
    private int stock = 1;

    public int getCdStock() {
        return stock;
    }

    public void buyCd() {
        stock = stock - 1;
    }
}
