package in.shaanu.d2dservice;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface loginApi {
    @FormUrlEncoded
    @POST("/shopper/public/index.php/api/customer/login")
    public void login(
            @Field("token") String token,
            @Field("u_mobile") String u_mobile,
            @Field("u_password") String u_password,
            Callback<Response> responseCallback

    );
}