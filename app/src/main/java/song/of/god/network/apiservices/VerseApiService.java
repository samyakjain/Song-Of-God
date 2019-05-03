package song.of.god.network.apiservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import song.of.god.database.entity.Verse;

public interface VerseApiService {
    @GET("/api/v1/chapters/{chapter}/verses")
    Call<List<Verse>> getListOfVerse(@Path("chapter") Integer chapter, @Query("access_token") String accessToken,
                                     @Query("language") String language);
}
