package song.of.god.application.Base;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void configureViews(int position, Object object);

    public Context getContext() {
        Context context = itemView.getContext();
        if (context instanceof AppCompatActivity) {
            return context;
        } else if (context instanceof ContextThemeWrapper) {
            return ((ContextThemeWrapper) context).getBaseContext();
        } else if (!(context instanceof Context)) {
            try {
                return (Context) ((Object) context).getClass().getMethod("getBaseContext").invoke(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return context;
    }

}
