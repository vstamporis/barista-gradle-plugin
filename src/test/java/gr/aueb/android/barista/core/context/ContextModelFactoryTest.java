package gr.aueb.android.barista.core.context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContextModelFactoryTest {

    private ContextModelFactory factory;

    @Before
    public void setup() {
        this.factory = new ContextModelFactory(null);
    }

    @Test
    public void getConnectivityModel() {
        Assert.assertNotNull(this.factory.getConnectivityModel(EnumTypes.ConnectivityType.POOR));
        Assert.assertNotNull(this.factory.getConnectivityModel(EnumTypes.ConnectivityType.RANDOM));
        Assert.assertNotNull(this.factory.getConnectivityModel(EnumTypes.ConnectivityType.GPS));
    }

    @Test
    public void getNavigationModel() {
        Assert.assertNotNull(this.factory.getNavigationModel(EnumTypes.NavigationType.FUZZ));
        Assert.assertNotNull(this.factory.getNavigationModel(EnumTypes.NavigationType.RANDOM_WALK));
        Assert.assertNull(this.factory.getNavigationModel(EnumTypes.NavigationType.DRIVING));
    }

    @Test
    public void getBatteryDrainModel() {
        Assert.assertNotNull(this.factory.getBatteryDrainModel());
    }
}