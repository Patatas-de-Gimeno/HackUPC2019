package com.example.hackupc;


import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 9999;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
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
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.bringToFront();
            //send image to API
            //get result
            String url = imageResult("");

          /* try {

                result = imageResult();
                result = result.getJSONObject("response");
                result = result.getJSONObject("solutions");
                result = result.getJSONObject("re_features_v3");
                resultArray = result.getJSONArray("detections");
                int length = resultArray.length();
                JSONObject tempPoint = null;
                JSONObject temp = null;
                String[][] resultData = new String[length][3];
                for (int i = 0; i < length ; i++) {
                    temp = (JSONObject) resultArray.get(i);
                    tempPoint = temp.getJSONObject("center_point");
                    resultData[i][0] = temp.getString("label");
                    resultData[i][1] = tempPoint.getString("y");
                    resultData[i][2] = tempPoint.getString("x");

                }

            } catch (JSONException e) {
            }*/

            //print cuadrados
        }
    }

    // devuelve dummy para pruebas
    public String imageResult(String url) {


            RequestQueue mQueue = Volley.newRequestQueue(this);



            String clientKey = "f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67";
            String modelID ="re_appliances";
            String imageURL="https://demo.restb.ai/images/demo/demo-2.jpg";

            String getURL = "https://api-eu.restb.ai/vision/v2/predict?client_key=" + clientKey + "&model_id=" + modelID + "&image_url=" + imageURL;

            JsonObjectRequest request = new com.android.volley.toolbox.JsonObjectRequest(Request.Method.GET, getURL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("RestResponse", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEH");
                    Log.e("Rest response", error.toString());
                }
            });
            mQueue.add(request);



/*            params.put("model_id", "re_appliances"); // set userId its a sample here
            params.put("image_url", "https://demo.restb.ai/images/demo/demo-2.jpg"); // set userId its a sample here
            params.put("client_key", "f59efc7d6b2dc753e9d803d64d4eb30981174252cc6b1d2c1a43fe2a4feedd67"); // set userId its a sample here
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (requestData.length() != 0) {
                    requestData.append('&');
                }
                // Encode the parameter based on the parameter map we've defined
                // and append the values from the map to form a single parameter
                requestData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                requestData.append('=');
                requestData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] requestDataByes = requestData.toString().getBytes("UTF-8");


            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                String jsonResponse = response.toString();
                return jsonResponse;
            }
            return null;
*/
return null;


    }

}