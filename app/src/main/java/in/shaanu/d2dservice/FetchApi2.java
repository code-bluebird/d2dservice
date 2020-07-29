package in.shaanu.d2dservice;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface FetchApi2 {
    @FormUrlEncoded
    @POST("/shopper/public/index.php/api/fetch_carpenter")
    public void fetch(
            @Field("area_id") String area_id,

            Callback<Response> callback);
}
