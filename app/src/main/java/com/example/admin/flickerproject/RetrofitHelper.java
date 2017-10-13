package com.example.admin.flickerproject;

import com.example.admin.flickerproject.model.PicRepo;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Admin on 10/13/2017.
 */

public class RetrofitHelper {

    private static final String BASE_URL = "http://api.flickr.com" ;

    public static Retrofit create(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Observable<PicRepo> getCall(){
        Retrofit retrofit = create();
        RequestService service = retrofit.create(RequestService.class);
        return service.getRequest();
    }

    public interface RequestService{
        @GET("/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1")
        Observable<PicRepo> getRequest();
    }
}