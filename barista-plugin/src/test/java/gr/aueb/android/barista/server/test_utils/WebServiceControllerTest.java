/**
 * Author: Tsiskomichelis Stelios
 * Created On: 21/10/2018
 * Project: butler-plugin
 * <p>
 * ClassName: testCOnnection
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.test_utils;
import gr.aueb.android.barista.emulator.adb.ADBClient;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import gr.aueb.android.barista.server.CommandResource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class WebServiceControllerTest extends JerseyTest {


    @Override
    protected Application configure() {

        return new ResourceConfig(CommandResource.class);

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
        assertEquals(CommandResource.GREETING_MSG,response);
    }

    @Test
    public void testChangeDimension(){

        ADBClient adbClient = ADBClient.getInstance();
        String token = adbClient.getTokenMap().keySet().iterator().next();

        String height = "500";
        String width = "600";

        Response r = target("/setDimension")
                .queryParam("token",token)
                .queryParam("height",height)
                .queryParam("width",width).request().get();
    }


    @Test
    public void resetDimension(){
        ADBClient adbClient = ADBClient.getInstance();
        String token = adbClient.getTokenMap().keySet().iterator().next();
        Response r = target("/reset")
                .queryParam("token",token)
                .request().get();
    }

    @Test
    public void setGeoFix(){
        double longt = 55.78851;
        double lat = 77.223456;

        ADBClient adbClient = ADBClient.getInstance();
        String token = adbClient.getTokenMap().keySet().iterator().next();
        System.out.println("Geofixing device :"+adbClient.verifyToken(token));

        target("/geofix")
                .queryParam("token",token)
                .queryParam("lat",lat)
                .queryParam("longt",longt)
                .request().get();

    }

    @Test
    public void getOverridenSizeTest(){

        ADBClient adbClient = ADBClient.getInstance();
        String token = adbClient.getTokenMap().keySet().iterator().next();
        adbClient.resetDimension(adbClient.verifyToken(token));

        String height = "500";
        String width = "600";

        Response re = target("/setDimension")
                .queryParam("token",token)
                .queryParam("height",height)
                .queryParam("width",width).request().get();

        WmSizeDTO r = target("/actualSize")
                .queryParam("token",token)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(WmSizeDTO.class);

        assertEquals(600,r.getWidth());
        assertEquals(500,r.getHeight());


    }
}
