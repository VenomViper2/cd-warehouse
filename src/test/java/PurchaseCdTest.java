import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PurchaseCdTest {

    @Test
    void customerCanBuyACdAndGetsAddedToCollection() {
        Charts charts = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);

        Customer customer = new Customer();
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7, charts, 10, priceChecker);
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertTrue(customer.hasCd(cd));
    }

    @Test
    void whenCustomerBuysCdStockIsLowered() {
        Charts charts = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);
        Cd cd = new Cd("David Bowie", "Hunky Dorey", 7, charts, 10, priceChecker);
        Customer customer = new Customer();
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        assertEquals(6, cd.getStock());
    }

    @Test
    void whenPurchaseSuccessfulChartsNotified() {
        Charts charts = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);

        Cd cd = new Cd("David Bowie", "Blackstar", 10, charts, 10, priceChecker );
        Customer customer = new Customer();
        Payment paymentStub = () -> true;
        cd.buy(customer, paymentStub);
        Mockito.verify(charts).notify("David Bowie", "Blackstar", 1);
    }


    @Test
    void whenInTopOneHundredPriceIsOnePoundLessThanLowestCompetitorPrice() {
        Charts chart = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);

        when(chart.getChartPosition()).thenReturn(1);
        when(priceChecker.getLowestCompetitorPrice()).thenReturn(6.0);

        Cd cd = new Cd("David Bowie", "Blackstar", 1, chart, 10, priceChecker);
        Customer customer = new Customer();
        Payment payment = () -> true;
        cd.buy(customer, payment);
        assertEquals(5, cd.getPrice());
    }

    @Test
    void whenInTopOneHundredAndPriceIs50PenceLessThanLowestCompetitorPricePriceIsStillLower() {
        Charts chart = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);
        when(chart.getChartPosition()).thenReturn(1);
        when(priceChecker.getLowestCompetitorPrice()).thenReturn(6.0);
        Cd cd = new Cd("David Bowie", "Blackstar", 1, chart, 5.50, priceChecker);
        Customer customer = new Customer();
        Payment payment = () -> true;
        cd.buy(customer, payment);
        assertEquals(5, cd.getPrice());

    }

    @Test
    void whenNotInTopOneHundredPriceIsNotChanged() {
        Charts chart = Mockito.mock(Charts.class);
        PriceChecker priceChecker = Mockito.mock(PriceChecker.class);
        Cd cd = new Cd("Keane", "Everybody's changing", 1, chart, 5, priceChecker);
        Customer customer = new Customer();
        Payment payment = () -> true;

        when(chart.getChartPosition()).thenReturn(500);

        cd.buy(customer, payment);
        assertEquals(5, cd.getPrice());
    }


}
