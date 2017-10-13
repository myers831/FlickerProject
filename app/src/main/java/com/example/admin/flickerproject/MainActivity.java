package com.example.admin.flickerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.flickerproject.model.Item;
import com.example.admin.flickerproject.model.PicRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static boolean item_click = false;
    public static final String TAG = "mainActivity";
    List<Item> itemList = new ArrayList<>();

    RecyclerView rvPicRepo;
    RecyclerviewAdapter recyclerviewAdapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPicRepo = findViewById(R.id.rvPicRepo);
        layoutManager = new LinearLayoutManager(this);

        RetrofitHelper.getCall()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PicRepo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(@NonNull PicRepo picRepo) {
                        for(Item item: picRepo.getItems()){
                            itemList.add(item);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                        recyclerviewAdapter = new RecyclerviewAdapter(itemList);
                        rvPicRepo.setLayoutManager(layoutManager);
                        rvPicRepo.setAdapter(recyclerviewAdapter);
                        Log.d(TAG, "onComplete: ");
                    }
                });

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
