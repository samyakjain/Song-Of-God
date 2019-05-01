package song.of.god.application;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private Context mContext;

    @Inject
    public RequestInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
