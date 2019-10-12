package com.example.hackupc;


import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 9999;
    String imageURL;
    ImageView imageView;
    String imageResult;
    ObjectLabelClass[] objectsDetecteds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
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

    public void OpenCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {

            imageView.setVisibility(View.VISIBLE);
            //Uri imag = data.getData();
            // imageView.setImageURI(imag);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.bringToFront();
            //send image to API
            //get result
            imageURL = "https://demo.restb.ai/images/demo/demo-2.jpg";
            imageResult();



        }
    }

    // devuelve dummy para pruebas
    void imageResult() {

        final ArrayList<ObjectLabelClass> furnitureList = new ArrayList<>();


        RequestQueue mQueue = Volley.newRequestQueue(this);


        String clientKey = "f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67";
        String modelID = "re_appliances";

        String getURL = "https://api-eu.restb.ai/vision/v2/predict?client_key=" + clientKey + "&model_id=" + modelID + "&image_url=" + imageURL;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("RestResponse", response.toString());
                imageResult = response.toString();
                if (imageResult != null) {
                    try {

                        JSONArray imageJArray = getImageJArray();

                        int length = imageJArray.length();

                        LinearLayout lay = (LinearLayout) findViewById(R.id.buttonslayouts);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);


                        for (int i = 0; i < length; i++) {
                            JSONObject tempObject = (JSONObject) imageJArray.get(i);
                            JSONObject tempObjectPoint = tempObject.getJSONObject("center_point");
                            ObjectLabelClass objLabelClass = new ObjectLabelClass(i, tempObject.getString("label"),
                                    Float.parseFloat(tempObjectPoint.getString("x")), Float.parseFloat(tempObjectPoint.getString("y")));

                            furnitureList.add(objLabelClass);
                            System.out.println(objLabelClass.Name);
                            // get base content a saber si funca y se printan los botones...
                            Button button = new Button(getApplicationContext());
                            button.setLayoutParams(lp);
                            button.setText(objLabelClass.Name);
                            button.setOnClickListener(new ButtonsOnClickListener());
                            lay.addView(button);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Rest response", error.toString());
            }
        });
        mQueue.add(request);
        authorizationImgur();
    }

    private JSONArray getImageJArray() throws JSONException {
        JSONObject imageJObject = new JSONObject(imageResult);
        imageJObject = imageJObject.getJSONObject("response");
        imageJObject = imageJObject.getJSONObject("solutions");
        imageJObject = imageJObject.getJSONObject("re_appliances");
        return imageJObject.optJSONArray("detections");
    }

    class ButtonsOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // SE ABRIRIA EL POPUP CON EL SHIPPING
        }
    }

    public void authorizationImgur() {
        RequestQueue mQueue = Volley.newRequestQueue(this);

        String endpoint = "https://api.imgur.com/oauth2/authorize";
        String clientId = "5491201f230a7da";
        String clientSecret = "62bc646c4c06210cdf3a54de8206e9e5727c8331";

        HashMap<String, String> hashM = new HashMap<>();
        hashM.put("response_type", "token");
        hashM.put("client_id", clientId);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, new JSONObject(hashM), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("DOOOOOOOOOOOOOOOOOOOOOOOOOOOOONE!");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest Response", error.toString());

            }
        });
        mQueue.add(request);
    }

    public void uploadImage(String path) {
        String url = "https://api.imgur.com/3/image";
        RequestQueue mQueue = Volley.newRequestQueue(this);
        HashMap<String, String> hashM = new HashMap<>();
        hashM.put("image", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(hashM), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("DOOOOOOOOOOOOOOOOOOOOOOOOOOOOONE!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest Response", error.toString());
            }
        });
        mQueue.add(request);

    }

}