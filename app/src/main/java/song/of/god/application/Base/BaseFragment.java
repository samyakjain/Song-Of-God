package song.of.god.application.Base;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    private Context mContext;
    protected View rootview;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        mContext=context;
    }

    public Context getmContext() {
        return mContext;
    }

    protected abstract ViewModel getViewModel();
    protected abstract void handleIncomingBundle();
    protected abstract void bindAndSetupUI();

}
