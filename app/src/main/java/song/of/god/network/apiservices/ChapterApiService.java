package song.of.god.network.apiservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import song.of.god.database.entity.Chapter;

public interface ChapterApiService {

    @GET("/api/v1/chapters")
    Call<List<Chapter>> getAllChapters(@Query("access_token") String accessToken,
                                       @Query("language") String language);
}
