package song.of.god.network.errorhandling;

import android.content.Context;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import song.of.god.network.errorhandling.error.OfflineException;
import song.of.god.util.NetUtil;

public class RestErrorInterceptor implements Interceptor {

    private final Context mContext;

    @Inject
    public RestErrorInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        if (!NetUtil.isNetworkAvailable(mContext)) {
            throw new OfflineException();
        } else {

            try {
                response = chain.proceed(request);
                if (!response.isSuccessful()) {

                    String packageName = "song.og.god.network.errorhandling.error.";

                    switch (response.code()) {
                        case 400: {
                            throw getExceptionObject(packageName+"BadRequestException",response);
                        }
                        case 403: {
                            throw getExceptionObject(packageName+"ForbiddenException",response);
                        }
                        case 404: {
                            throw getExceptionObject(packageName+"NotFoundException",response);
                        }
                        case 500: {
                            throw getExceptionObject(packageName+"InternalServerException",response);
                        }
                        case 502: {
                            throw getExceptionObject(packageName+"BadGatewayException",response);
                        }
                        default: {
                            throw getExceptionObject(packageName+"UnknownException",response);
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    private Exception getExceptionObject(String className, Response response) {
        try {
            Class<?> myClass = Class.forName(className);
            Constructor<?> ctr = myClass.getConstructor(String.class);
            Object object;

            if (response.message() != null && !response.message().isEmpty()) {
                String arg1 = response.message();
                object = ctr.newInstance(new Object[]{arg1});
            } else {
                object = ctr.newInstance();
            }
            return (Exception) object;
        }catch (Exception e){
            e.printStackTrace();
            return e;
        }
    }
}
