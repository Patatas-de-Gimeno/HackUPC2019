package com.example.hackupc;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


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


public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 9999;
    String imageURL;
    ImageView imageView;
    String imageResult;
    ArrayList<ObjectLabelClass> furnitureList;
    ObjectLabelClass[] objectsDetecteds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckAndAskPermisions();
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    private void CheckAndAskPermisions(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            System.out.println("Error en los permisos de cámara");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 10);
            }

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
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.bringToFront();

            authorizationImgur();
            uploadImg(bitmap);

            //send image to API
            //get result
            imageURL = "https://demo.restb.ai/images/demo/demo-2.jpg";
            imageResult();
        }
    }

    private void uploadImg(Bitmap bitmap) {
        String clientId = "de252006156e143";
        RequestQueue mQueue = Volley.newRequestQueue(this);
        Authentication auth = new Authentication("https://api.imgur.com/3/upload");
        auth.putHead("Authorization", "Client-ID {{de252006156e143}}");
        auth.putHead("image", bitmap);
        auth.putHead("type", "file");
        auth.putHead("name", "01.jpg");
        auth.putHead("name", "Prova 01");
        JsonObjectRequest request = auth.request(Request.Method.POST, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
            }
        });
        mQueue.add(request);

    }

    private void authorizationImgur() {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String endpoint = "https://api.imgur.com/oauth2/authorize";
        String clientId = "de252006156e143";
        String acces_token = "358d129d7bcb4ff49575155aed0153c7d999c93e";
        String pin = "c7e78d71cc";
        Authentication auth = new Authentication(endpoint);
        auth.putHead("response_type", acces_token);
        auth.putHead("client_id", clientId);
        JsonObjectRequest request    = auth.request(Request.Method.POST, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("AUTHENTICATIOOOOOOOOOOOOOOOOOOOOOOOON");
            }
        });
        mQueue.add(request);
    }

    // devuelve dummy para pruebas
    void imageResult() {

        furnitureList = new ArrayList<>();


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

                        LinearLayout lay = findViewById(R.id.buttonslayouts);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
                        Display display = getWindowManager().getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        int width = size.x;
                        int height = size.y;
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
                            button.setId(i);
                            button.setOnClickListener(new ButtonsOnClickListener());
                            button.setBackgroundColor(Color.RED);
                            button.setX(objLabelClass.Position_X*width);
                            button.setY(objLabelClass.Position_Y*height);
                            lay.addView(button);
                        }
                        lay.bringToFront();


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

    }

    private JSONArray getImageJArray() throws JSONException {
        JSONObject imageJObject = new JSONObject(imageResult);
        imageJObject = imageJObject.getJSONObject("response");
        imageJObject = imageJObject.getJSONObject("solutions");
        imageJObject = imageJObject.getJSONObject("re_appliances");
        return imageJObject.optJSONArray("detections");
    }

    class ButtonsOnClickListener implements View.OnClickListener {

        private PopupWindow mPopupWindow;
        @Override
        public void onClick(View v) {
            System.out.println("Boton pulsado");
            int buttonId = v.getId();
            ObjectLabelClass selectedObject = furnitureList.get(buttonId);
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflate the custom layout/view
            View customView = inflater.inflate(R.layout.popup_layout,null);
            mPopupWindow = new PopupWindow(customView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

            // Set a click listener for the popup window close button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    mPopupWindow.dismiss();
                }
            });
            mPopupWindow.showAtLocation(imageView, Gravity.CENTER, 0, 0);
            customView.bringToFront();
        }
    }



}