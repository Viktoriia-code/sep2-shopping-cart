import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    @Test
    void getItemCost() {
        assertEquals(10, ShoppingCart.getItemCost(2, 5));
    }

    @Test
    void getTotalCost() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertEquals(0, shoppingCart.getTotalCost());
    }
}