package com.vgilanza.app.vigilanza.app;
import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aswin on 27-Jan-17.
 */
public class AppController extends Application {
    public static final String TAG=AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance =this;
    }
    public static synchronized AppController getInstance(){
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public void addToRequestQueue(Request request,String tag){
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }
    public void addToRequestQueue(Request request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Object tag){
        if(mRequestQueue !=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
