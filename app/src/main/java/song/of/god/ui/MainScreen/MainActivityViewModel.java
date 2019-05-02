package song.of.god.ui.MainScreen;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import song.of.god.application.Base.BaseViewModel;
import song.of.god.database.entity.Chapter;
import song.of.god.network.Resource;

public class MainActivityViewModel extends BaseViewModel {

    @Inject
    public MainActivityViewModel() {
    }

    @Inject
    MainActivityRepository mainActivityRepository;

    private MutableLiveData<Resource<List<Chapter>>> chapterList = new MutableLiveData<>();

    private MainActivityRepository getMainActivityRepository() {
        return mainActivityRepository;
    }

    public MutableLiveData<Resource<List<Chapter>>> getChapterList() {
        return chapterList;
    }

    public void fetchAllChapters() {
        mainActivityRepository.getAllChapters().observeForever(new Observer<Resource<List<Chapter>>>() {
            @Override
            public void onChanged(Resource<List<Chapter>> listResource) {
                getChapterList().postValue(listResource);
            }
        });
    }
}
