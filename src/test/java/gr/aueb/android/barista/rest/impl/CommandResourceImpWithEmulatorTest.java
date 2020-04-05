package gr.aueb.android.barista.rest.impl;


import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.WmGetSize;
import gr.aueb.android.barista.core.model.WmSizeReset;
import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import gr.aueb.android.barista.server.BaristaApplication;
import gr.aueb.android.barista.server.MyObjectMapperProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandResourceImpWithEmulatorTest extends JerseyTest {

    private static EmulatorManager emulator;

    @BeforeClass
    public static void initialize(){
        emulator = EmulatorManager.getManager();
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new BaristaApplication();

    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(new JacksonFeature()).register(MyObjectMapperProvider.class);
    }

    CommandExecutor commandExecutorImpl;

    @Before
    public void setup(){
        // FIXME some times Class Cast exceptions is thrown. Must investigate
        commandExecutorImpl =  CommandExecutorFactory.getCommandExecutor();

    }

    private WmGetSize getScreenSize(String token){
        WmGetSize getActualSize = new WmGetSize(token);
        commandExecutorImpl.executeAdbCommand(getActualSize);
        return getActualSize;
    }

    private void resetScreenSize(String token){
        WmSizeReset sizeReset = new WmSizeReset(token);
        commandExecutorImpl.executeAdbCommand(sizeReset);
    }

    @Test
    public void executeSetSizeCommandWithREST(){

        String token = EmulatorManager.getManager().getTokenMap().keys().nextElement();

        resetScreenSize(token);

        CommandDTO commandDTO = new WmSizeDTO(token, 1280, 800, false, DimensionUnit.DPI.toString());

        Entity entity = Entity.entity(commandDTO, MediaType.APPLICATION_JSON_TYPE);
        System.out.println(entity.getEntity());

        Response response = target("/execute")
                            .request()
                            .post(Entity.entity(commandDTO, MediaType.APPLICATION_JSON_TYPE));

        System.out.println(response.getEntity().toString());

        assertThat(response.getStatus(), is(equalTo(200)));

        WmGetSize actualSize = getScreenSize(token);
        assertThat(actualSize.getHeight(),is(equalTo(800)));

        resetScreenSize(token);

    }

    @Test
    public void executeGeoFixCommandWithREST(){

        String token = EmulatorManager.getManager().getTokenMap().keys().nextElement();
        CommandDTO geofix = new GeoFixDTO(token,62.888888, 102.42999);


        Entity entity = Entity.entity(geofix, MediaType.APPLICATION_JSON_TYPE);
        System.out.println(entity.getEntity());

        Response response = target("/execute")
                .request()
                .post(Entity.entity(geofix, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(equalTo(200)));

    }

    @Test
    public void executeActivate(){
//        ADBClient.getInstance().setPackageName("com.example.stsisko.helloworldgradle");
//             Response response = target("/activate")
//                .request()
//                .get();
//        assertThat(response.getStatus(), is(equalTo(200)));

    }


}
