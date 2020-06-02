package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Test;

public class RandomConnectivityModelTest {

    @Test
    public void getConnectivityCommand() {
        RandomConnectivityModel model = new RandomConnectivityModel(null);

        Assert.assertNotNull(model.next(1));
    }
}