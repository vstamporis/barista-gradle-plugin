package gr.aueb.android.barista.server;

import gr.aueb.android.barista.rest.impl.DebugExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BaristaApplication extends ResourceConfig {
    public BaristaApplication() {
        super(
                CommandResource.class, DebugExceptionMapper.class,
                // register Jackson ObjectMapper resolver
                MyObjectMapperProvider.class,
                JacksonFeature.class
        );
    }
}