package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Test;

public class FuzzMovementModelTest {

    @Test
    public void getRandomMovement() {
        FuzzMovementModel model = new FuzzMovementModel(null);

        Assert.assertNotNull(model.next(1));
    }
}