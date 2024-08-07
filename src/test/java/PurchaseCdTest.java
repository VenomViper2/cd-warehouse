import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseCdTest {

    @Test
    void customerCanBuyACdAndGetsAddedToCollection() {
        Customer customer = new Customer();
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7);
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertTrue(customer.hasCd(cd));
    }

    @Test
    void whenCustomerBuysCdStockIsLowered() {
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7);
        Customer customer = new Customer();
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertEquals(6, cd.getStock());
    }
}
