/**
 * Author: Tsiskomichelis Stelios
 * Created On: 21/10/2018
 * Project: butler-plugin
 * <p>
 * ClassName: testCOnnection
 * Role:
 * Description:
 */
package server.test_utils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;
import server.WebServiceController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ConnectionTest extends JerseyTest {

//    public ConnectionTest() {
//        super();
//    }
//
//    public ConnectionTest(TestContainerFactory testContainerFactory) {
//        super(testContainerFactory);
//    }
//
//    public ConnectionTest(Application jaxrsApplication) {
//        super(jaxrsApplication);
//    }


    //private static String HELLO_URL = "http://localhost:8080/status";

    @Override
    protected Application configure() {

        return new ResourceConfig(WebServiceController.class);

    }

    @Test
    public void testConnection() throws Exception {
      Response response =  target("status").request().get();
      assertNotNull(response);
      assertEquals(200,response.getStatus());

    }

    @Test
    public void testServiceFunctionality(){
        String response =  target("status").request().get(String.class);
        assertNotNull(response);
        assertEquals(WebServiceController.GREETING_MSG,response);
    }
}
