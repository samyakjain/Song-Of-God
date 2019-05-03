package song.of.god.ui.ChapterDetailScreen;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import song.of.god.application.Base.BaseViewModel;
import song.of.god.database.entity.Verse;
import song.of.god.network.Resource;

public class ChapterDetailViewModel extends BaseViewModel {

    @Inject
    public ChapterDetailViewModel() {
    }

    @Inject
    ChapterDetailViewRepository chapterDetailViewRepository;

    private MutableLiveData<Resource<List<Verse>>> verseList = new MutableLiveData<>();

    private ChapterDetailViewRepository getChapterDetailViewRepository() {
        return chapterDetailViewRepository;
    }

    public MutableLiveData<Resource<List<Verse>>> getChapterList() {
        return verseList;
    }

    public void fetchAllChapters(Integer chapterNumber) {
        chapterDetailViewRepository.getAllChapters(chapterNumber).observeForever(listResource -> getChapterList().postValue(listResource));
    }
}
