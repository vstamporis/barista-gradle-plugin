package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Test;

public class BatteryDrainModelTest {

    @Test
    public void getBatteryCommand() {
        BatteryDrainModel model = new BatteryDrainModel(null);

        Assert.assertNotNull(model.next(1));
    }
}