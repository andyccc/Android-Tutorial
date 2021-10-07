package com.example.volleydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testImage();
        testRefresh();
    }

    void testRefresh() {
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testGlide();

            }
        });

    }

    void closeRefresh() {
        // 关闭刷新
        if (null!= swipeRefreshLayout && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    void testGlide() {
        String url = "https://cdn.pixabay.com/photo/2021/08/15/06/54/sunflower-6546993_150.jpg";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_background)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        closeRefresh();

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        closeRefresh();

                        return false;
                    }
                }).into(imageView);

    }


    void testImage2() {
        NetworkImageView imageView2 = findViewById(R.id.imageView2);

        String url = "https://cdn.pixabay.com/photo/2021/08/15/06/54/sunflower-6546993_150.jpg";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache = new LruCache<>(50);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
        imageView2.setImageUrl(url, imageLoader);

    }

    void testImage() {

        ImageView imageView = findViewById(R.id.imageView);

        String url = "https://cdn.pixabay.com/photo/2021/08/15/06/54/sunflower-6546993_150.jpg";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache = new LruCache<>(50);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });

        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errorTag", "onErrorResponse: ", error);
            }
        });


    }

    void testHttp() {
        TextView textView = findViewById(R.id.textView);

        String url = "https://www.baidu.com";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errtag", "onErrorResponse: ", error);
                    }
                }
        );

        queue.add(stringRequest);
    }
}