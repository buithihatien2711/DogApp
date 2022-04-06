package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dogapp.databinding.ActivityMainBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.view.DogAdapter;
import com.example.dogapp.viewmodel.DogsApi;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private DogsApiService apiService;
    ActivityMainBinding binding;
    List<DogBreed> listDogBreed  = new ArrayList<DogBreed>();
    DogAdapter dogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        apiService = new DogsApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        for (DogBreed dog : dogBreeds) {
                            Log.d("Debug1", dog.getName());
                            listDogBreed.add(dog);
                        }
                        dogAdapter = new DogAdapter(listDogBreed, MainActivity.this);
                        binding.rvDogs.setAdapter(dogAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG1", e.getMessage());
                    }
                });


//        binding.rvDogs.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvDogs.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

//    private void extractDog(){
//        apiService = new DogsApiService();
//
//        apiService.getDogs()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
//                    @Override
//                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
//                        for (DogBreed dog : dogBreeds) {
//                            Log.d("Debug1", dog.getName());
//                            listDogBreed.add(dog);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d("DEBUG1", e.getMessage());
//                    }
//                });
//    }

}