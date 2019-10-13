package com.example.hackupc;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Environment;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import android.widget.TextView;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 9999;
    String imageURL;
    ImageView imageView;
    String imageResult;
    ArrayList<ObjectLabelClass> furnitureList;
    ObjectLabelClass[] objectsDetecteds;
    StorageReference mStorageRef;
    FirebaseAuth mAuth;
    GoogleSignInOptions mGoogleSignInOptions;
    GoogleSignInClient mGoogleSignInClient;

    private static final String TAG = "AnonymousAuth";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckAndAskPermisions();
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        mStorageRef = FirebaseStorage.getInstance().getReference();

      //  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      //          .requestIdToken("")
       //         .requestEmail()
        //        .build();

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("961969300443-o775mp7g3vi9rgeakr6gs841u1qnchqo.apps.googleusercontent.com"
                )
                .requestEmail()
                .build();
        mAuth = FirebaseAuth.getInstance();
        signInAnonymously();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
    }

    private void signInAnonymously() {

        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
        // [END signin_anonymously]

    }

    private void CheckAndAskPermisions(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            System.out.println("Error en los permisos de cámara");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.BLUETOOTH}, 10);
            }

        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Error en los permisos de cámara");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
            }
        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Error en los permisos de cámara");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.BLUETOOTH}, 10);
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

            //authorizationImgur();
            uploadImg(bitmap);

            //send image to API
            //get result
            imageURL = "https://demo.restb.ai/images/demo/demo-2.jpg";
            imageResult();
        }
    }
    String currentPhotoPath;

    private File createImageFile() throws IOException {
// Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void uploadImg(Bitmap bitmap) {
        /*
        String clientId = "ed40c99963dc11a";
        RequestQueue mQueue = Volley.newRequestQueue(this);
        Authentication auth = new Authentication("https://api.imgur.com/3/upload");
        auth.putHead("Authorization", "Client-ID {{ed40c99963dc11a}}");
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
        mQueue.add(request);*/
        /*try {
            FileOutputStream out = new FileOutputStream("filename");

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //File filee = saveBitmap(bitmap, "../photo.jpg");
        /*tring path = Environment.getDataDirectory().getPath();

        //saveBitmap(bitmap, "images/pics.jpg");




        //File file = createImageFile();


        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath() + "/imgs/");
        dir.mkdir();
        File file = new File(dir, System.currentTimeMillis() + ".jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, );
        //fOutputStream.flush();
        //fOutputStream.close();

        //MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

        //Uri uri = Uri.fromFile(file);
        mStorageRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                System.out.println(downloadUrl.toString());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });



*/
        try {
            File file = createImageFile();
            Uri uriFile = Uri.fromFile(file);
            StorageReference picsRef = mStorageRef.child("pics.jpg");
            picsRef.putFile(uriFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getUploadSessionUri().normalizeScheme();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void saveImage(Bitmap finalBitmap, String image_name) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File saveBitmap(Bitmap bitmap, String path) {
        File file = new File(path);
        try {
            FileOutputStream outputStream = null;
            try {

                outputStream = openFileOutput("image.jpg", MODE_PRIVATE); //here is set your file path where you want to save or also here you can set file object directly
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // bitmap is your Bitmap instance, if you want to compress it you can compress reduce percentage
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("EERRROOOOOOOR");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    private void authorizationImgur() {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String endpoint = "https://api.imgur.com/oauth2/authorize";
        String clientId = "ed40c99963dc11a";
        String clientSecretId = "95c35b6bd8f337718366c0af80b795506f1d739c";
        String acces_token = "358d129d7bcb4ff49575155aed0153c7d999c93e";
        String pin = "c7e78d71cc";
        Authentication auth = new Authentication(endpoint);
        auth.putHead("client-ID", "Client-ID ed40c99963dc11a");
        //auth.putHead("response_type", acces_token);
        //auth.putHead("client_id", clientId);
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
// Accion al abrir un boton de objeto: muestra la lista de productos.
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
            TextView titleList = customView.findViewById(R.id.titlelist);
            titleList.setText("Lista de productos");
            mPopupWindow.showAtLocation(imageView, Gravity.TOP, 0, 0);
            customView.bringToFront();
            ArrayList<Producto> list = DataProductos.ReturnData(selectedObject.Name);
            ListView mListView = (ListView) customView.findViewById(R.id.lista_productos);
            ProductoListAdapter adapter = new ProductoListAdapter(customView.getContext(), R.layout.rowproducts, list);
            mListView.setAdapter(adapter);
        }
    }



}