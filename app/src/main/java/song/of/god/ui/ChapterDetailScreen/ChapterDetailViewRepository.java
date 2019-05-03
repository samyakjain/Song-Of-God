package song.of.god.ui.ChapterDetailScreen;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import song.of.god.database.dao.TokenDao;
import song.of.god.database.dao.VerseDao;
import song.of.god.database.entity.Verse;
import song.of.god.network.NetworkBoundResource;
import song.of.god.network.Resource;
import song.of.god.network.apiservices.VerseApiService;

public class ChapterDetailViewRepository {

    @Inject
    public ChapterDetailViewRepository() {
    }

    @Inject
    VerseDao verseDao;

    @Inject
    TokenDao tokenDao;

    @Inject
    VerseApiService verseApiService;

    public LiveData<Resource<List<Verse>>> getAllChapters(Integer chapterNumber) {
        return new NetworkBoundResource<List<Verse>, List<Verse>>() {
            @Override
            protected void saveCallResult(@NonNull List<Verse> item) {
                verseDao.insertVerses(item);
            }

            @NonNull
            @Override
            protected LiveData<List<Verse>> loadFromDb() {
                return verseDao.getAllVersesForAChapter(chapterNumber);
            }

            @NonNull
            @Override
            protected Call<List<Verse>> createCall() {
                return verseApiService.getListOfVerse(chapterNumber, tokenDao.getLatestToken().getAccessToken(), "hi");
            }
        }.getAsLiveData();
    }
}
