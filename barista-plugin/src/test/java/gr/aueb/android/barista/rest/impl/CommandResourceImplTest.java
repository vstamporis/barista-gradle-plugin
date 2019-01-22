package gr.aueb.android.barista.rest.impl;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.WmDensityDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import gr.aueb.android.barista.server.BaristaApplication;
import gr.aueb.android.barista.server.MyObjectMapperProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandResourceImplTest extends JerseyTest {

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

    CommandExecutorImplStub commandExecutorImplStub;

    @Before
    public void setup(){

        commandExecutorImplStub = new CommandExecutorImplStub();
        // Configure CommandExecutorImpl
        CommandExecutorFactory.setStub(commandExecutorImplStub);

    }

    @Test
    public void executeSingleCommand(){

        CommandDTO commandDTO = new WmSizeDTO("1", 1280, 800, false, DimensionUnit.DPI.toString());

        Entity entity = Entity.entity(commandDTO, MediaType.APPLICATION_JSON_TYPE);
        System.out.println(entity.getEntity());

        Response response = target("/execute").request().post(Entity.entity(commandDTO, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(commandExecutorImplStub.commands.size(), is(equalTo(1)));

    }

    @Test
    public void executeCommandList(){

        CommandDTO commandDTO = new WmSizeDTO("1", 1280, 800, false, DimensionUnit.DPI.toString());
        List<CommandDTO> commandList = new ArrayList<>();
        commandList.add(new WmSizeDTO("1", 1280, 800, false, DimensionUnit.DPI.toString()));
        commandList.add(new WmDensityDTO("1", 240));
        commandList.add(new WmSizeDTO("2", 2048, 1560, false, DimensionUnit.PIXEL.toString()));
        commandList.add(new WmDensityDTO("2", 320));

        Response response = target("/executeAll").request().post(Entity.entity(new GenericEntity<List<CommandDTO>>(commandList){}, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(commandExecutorImplStub.commands.size(), is(equalTo(4)));

    }
}
