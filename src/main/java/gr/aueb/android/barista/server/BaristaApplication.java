package gr.aueb.android.barista.server;

import gr.aueb.android.barista.server.impl.DebugExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BaristaApplication extends ResourceConfig {

    /**
     *  Function that initializes a Jackson application that serves a REST API defined in CommandResource class and
     *  uses the MyObjectMapperPrivider in order to transform JSON requests to model objects.
     */
    public BaristaApplication() {
        super(
                CommandResource.class, DebugExceptionMapper.class,
                // register Jackson ObjectMapper resolver
                MyObjectMapperProvider.class,
                JacksonFeature.class
        );
    }
}