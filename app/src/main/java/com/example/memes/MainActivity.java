package com.example.memes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
  String Url;
  Button nxtBtn, shareBtn;
  ImageView img;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF00FF"));
        actionBar.setBackgroundDrawable(colorDrawable);
           // #FFBB89FC

        nxtBtn = findViewById(R.id.btnNext);
        shareBtn = findViewById(R.id.btnShare);
        progressBar = findViewById(R.id.progressBar2);
        img = findViewById(R.id.MemeImageView);
        loadMeme();
    }

 public void loadMeme(){
      progressBar.setVisibility(View.VISIBLE);
    // RequestQueue queue = Volley.newRequestQueue(this);
     String url ="https://meme-api.herokuapp.com/gimme";


     JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
             (Request.Method.GET, url, null, response -> {
                 try {

                    Url = response.getString("url");
                     ImageView imageView = findViewById(R.id.MemeImageView);
                     Glide.with(this).load(Url).listener(new RequestListener<Drawable>() {

                         @Override
                         public boolean onLoadFailed( GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                             progressBar.setVisibility(View.GONE);
                             return false;
                         }
                         @Override
                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             progressBar.setVisibility(View.GONE);
                             return false;
                         }}).into(imageView);
                   // progressBar.setVisibility(View.GONE);
                 } catch (JSONException e) {
                     e.printStackTrace();
                     Log.d(" Kuch Gadbad h", String.valueOf(this));
                 }
                 }, error -> {
                 // TODO: Handle error
            Log.d("Something went Wrong", String.valueOf(this));
             });
    // progressBar.setVisibility(View.GONE);
   //  queue.add(jsonObjectRequest);
     MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
 }
 public void shareMeme(View view){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

    // intent.putExtra(Intent.EXTRA_TEXT,Url);
     intent.putExtra(Intent.EXTRA_TEXT,"Hey I found this cool meme..."+Url);
     Intent Chooser = Intent.createChooser(intent,"share this meme...");
     startActivity(Chooser);

 }
 public void nextMeme( View view){

        loadMeme();
 }
}