package com.example.hackupc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 9999;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
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
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST){
           Bitmap bitmap= (Bitmap) data.getExtras().get("data");
           imageView.setImageBitmap(bitmap);
           //send image to API
            //get result
            JSONObject result = null;
            JSONArray resultArray = null;
            try {
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

            }
            //print cuadrados
        }
    }

// devuelve dummy para pruebas
    public JSONObject imageResult(){
        try {
            return new JSONObject(
                    "{" +
                         "   \"error\": \"false\","+
                    "\"correlation_id\": \"4e97bcbb-53af-4528-a22f-d46f2ce23db6\","+
                    "\"version\": \"2\","+
                    "\"time\": \"2018-10-02T13:09:40.448881\","+
                    "\"response\": {"+
                "\"solutions\": {"+
                    "\"re_features_v3\": {" +
                        "\"detections\": [ " +
                        "{" +
"                            \"center_point\": {"+
                            "\"y\": 0.8842905405405406,"+
                         "           \"x\": 0.35208098987626546"+
                        "},"+
                            "\"label\": \"hardwood_floor\""+
                        "},"+
                        "{"+
                            "\"center_point\": { "+
                            "\"y\": 0.18412162162162163,"+
                         "           \"x\": 0.5067491563554556"+
                        "},"+
                            "\"label\": \"coffered_ceiling\""+
                        "},"+
                        "{"+
                            "\"center_point\": {"+
                           " \"y\": 0.18158783783783783,"+
                         "           \"x\": 0.5573678290213724"+
                        "},"+
                       "     \"label\": \"beamed_ceiling\" "+
                        "}"+
                "]"+
                    "}"+
                "}"+
            "}"+
"}"
            );

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}

