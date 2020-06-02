package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Test;

public class RandomWalkModelTest {

    @Test
    public void getWalkStep() {
        RandomWalkModel model = new RandomWalkModel(null, 37.975305, 23.734697);

        Assert.assertNotNull(model.next(1));
    }
}