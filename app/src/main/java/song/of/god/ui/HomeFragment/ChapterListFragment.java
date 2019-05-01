package song.of.god.ui.HomeFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModel;
import song.of.god.R;
import song.of.god.application.Base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterListFragment extends BaseFragment {


    public ChapterListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_list, container, false);
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void handleIncomingBundle() {

    }

    @Override
    protected void bindAndSetupUI() {

    }
}
