import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseCdTest {

    @Test
    void customerCanBuyACdAndGetsAddedToCollection() {
        Charts charts = Mockito.mock(Charts.class);
        Customer customer = new Customer();
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7, charts );
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertTrue(customer.hasCd(cd));
    }

    @Test
    void whenCustomerBuysCdStockIsLowered() {
        Charts charts = Mockito.mock(Charts.class);
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7, charts);
        Customer customer = new Customer();
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertEquals(6, cd.getStock());
    }

    @Test
    void whenPurchaseSuccessfulChartsNotified() {
        Charts charts = Mockito.mock(Charts.class);
        Cd cd = new Cd("David Bowie", "Blackstar", 10, charts);
        Customer customer = new Customer();
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        Mockito.verify(charts).notify("David Bowie", "Blackstar", 1);
    }




}
