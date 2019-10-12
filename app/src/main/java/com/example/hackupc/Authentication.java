package com.example.hackupc;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

import javax.xml.transform.ErrorListener;

public class Authentication {
    public String endpoint;
    HashMap<String, String> hashM;

    public Authentication(String endpoint) {
        this.endpoint = endpoint;
        this.hashM = new HashMap<>();
    }

    public void putHead(String key, String value) {
        this.hashM.put(key, value);
    }

    public JsonObjectRequest request(int method, Response.Listener<JSONObject> listener) {

        return new JsonObjectRequest(method, this.endpoint, new JSONObject(this.hashM), listener,  new ErrorAuthenticationListener());

    }

    public class ErrorAuthenticationListener implements Response.ErrorListener {

        ErrorAuthenticationListener(){

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Rest Response", error.toString());
        }
    }
}
