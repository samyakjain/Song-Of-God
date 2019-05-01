package song.of.god.network.apiservices;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import song.of.god.database.entity.Token;

public interface TokenApiService {

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<Token> getToken(@Field("client_id") String clientId,
                         @Field("client_secret") String clientSecret,
                         @Field("grant_type") String grantType,
                         @Field("scope") String scope
    );


}
