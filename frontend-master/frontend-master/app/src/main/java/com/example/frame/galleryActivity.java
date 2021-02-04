package com.example.frame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
//import android.util.Log;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class galleryActivity extends AppCompatActivity {

    List<Cell> allFilesPaths;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        String TAG = "showImages()";
        Log.e(TAG, "galleryActivity --- start of onCreate() ---");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);


        //FOR STORAGE PERMISSION
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
        }else
        {
            //SHOW THE IMAGES
            showImages();
        }

        Log.e(TAG, "galleryActivity --- end of onCreate() ---");
    }

    //METHOD FOR SHOWING THE IMAGES ON SCREEN
    private void showImages(){

        String TAG = "showImages()";
        Log.e(TAG, "galleryActivity --- showImages start of showImages() ---");

        //THIS IS FOR FINDING THE FOLDER WITH IMAGES
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Images/";
        allFilesPaths = new ArrayList<>();
        allFilesPaths = listAllFiles(path);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);
        //MAKE THE LIST WITH 3 COLUMNS
         RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);;

        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(),cells);
        recyclerView.setAdapter(adapter);

        Log.e(TAG, "galleryActivity --- showImages end of showImages() ---");
    }
    private ArrayList<Cell> prepareData(){
        ArrayList<Cell> allImages = new ArrayList<>();
        for(Cell c: allFilesPaths){
            Cell cell = new Cell();
            cell.setTitle(c.getTitle());
            cell.setPath(c.getPath()) ;
            allImages.add(cell);
        }
        return allImages;
    }

    //METHOD FOR LOADING ALL FILES FROM THE FOLDER
    private List<Cell> listAllFiles(String pathName){
        List<Cell> allFiles = new ArrayList<>();
        File file = new File(pathName);
        File[] files = file.listFiles();
        if(files != null){
            for(File f: files){
                Cell cell = new Cell();
                cell.setTitle(f.getName());
                cell.setPath(f.getAbsolutePath()) ;
                allFiles.add(cell);
            }
        }
        return allFiles;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if( requestCode == 1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //SHOWS THE IMAGES
                showImages();
            }else{
                Toast.makeText(this,"Permission not granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}