import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseTest {

    @Test
    void customerCanPurchaseCDFromWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.buyCd();
        assertEquals(0, warehouse.getCdStock());
    }
}
