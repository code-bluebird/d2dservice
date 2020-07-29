package in.shaanu.d2dservice;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface registerApi {
    @FormUrlEncoded
    @POST("/shopper/public/index.php/api/customer/add_user")
    public void register(
            @Field("token") String token,
            @Field("u_name") String u_name,
            @Field("u_mobile") String u_mobile,
            @Field("u_email") String u_email,
            @Field("u_password") String u_password,
            Callback<Response> responseCallback

    );
}
