package com.example.hackupc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mQueue = Volley.newRequestQueue(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
        }

        //putKey();
        jsonParse();

    }

    private void putKey() {
        String url = "https://api-us.restb.ai/vision/v2/predict";
        String query = "client_key=f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67";
        byte[] buffer = new byte[1000];
        try {
            InputStream response = new URL(url + "?" + query).openStream();
            response.read(buffer);
            response.close();
            System.out.print(Arrays.toString(buffer));
        } catch (Exception e) {
            System.out.println("FAIL");
            e.printStackTrace();

        }
    }
    private void jsonParse() {
        String url = "https://api-eu.restb.ai/vision/v2/predict";

        HashMap<String, String> hashM = new HashMap<>();
        //hashM.put("model_id", "real_estate_global_v2");
        //hashM.put("image_url", "https://demo.restb.ai/images/demo/demo-1.jpg");
        hashM.put("client_key", "f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(hashM),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Rest Response", response.toString());
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEH");
                Log.e("Rest response", error.toString());
                // TODO: Handle error
            }
        });

        mQueue.add(request);
    }

    public void multipredict() {
        String url = "https://api-us.restb.ai/vision/v2/multipredict";
        String query = "model_id=real_estate_global_v2,re_features_v3,re_appliances"
                + "&image_url=https://demo.restb.ai/images/demo/demo-1.jpg"
                + "&client_key=f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67";

        InputStream response = null;
        try {
            response = new URL(url + "?" + query).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
