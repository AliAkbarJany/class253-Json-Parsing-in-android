package com.rafsan.class253jsonparsinginandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button buttonLoad;

    TextView textName,textPhone,textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        buttonLoad = findViewById(R.id.buttonLoad);

        textName = findViewById(R.id.textName);
        textPhone = findViewById(R.id.textPhone);
        textEmail = findViewById(R.id.textEmail);

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                progressBar.setVisibility(View.VISIBLE);

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ali71.000webhostapp.com/apps/first.json";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://ali71.000webhostapp.com/apps/first.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressBar.setVisibility(View.GONE);

                                Log.d("serverRes",response);

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    String name = jsonObject.getString("name");
                                    String phone = jsonObject.getString("phone");
                                    String email = jsonObject.getString("email");

                                    textName.setText(name);
                                    textPhone.setText(phone);
                                    textEmail.setText(email);
                                }
                                catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        buttonLoad.setText("Volley Error");

                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });


    }
}