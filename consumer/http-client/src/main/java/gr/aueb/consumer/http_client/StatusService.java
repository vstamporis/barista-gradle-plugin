package gr.aueb.consumer.http_client;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatusService {

    @GET("status")
    Call<String> getStatusMessage();

    @GET("status2")
    Call<String> getStatusMessage2();
}
