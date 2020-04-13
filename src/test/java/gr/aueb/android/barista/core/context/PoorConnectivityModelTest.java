package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Test;

public class PoorConnectivityModelTest {

    @Test
    public void getConnectivityCommand() {
        PoorConnectivityModel model = new PoorConnectivityModel(null);

        Assert.assertNotNull(model.next(1));
    }
}