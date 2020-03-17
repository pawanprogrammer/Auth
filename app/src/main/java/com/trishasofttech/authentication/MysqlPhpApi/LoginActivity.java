package com.trishasofttech.authentication.MysqlPhpApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trishasofttech.authentication.MainActivity;
import com.trishasofttech.authentication.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button create, login;
    private EditText email, password;
    private String url = "http://searchkero.com/rachit/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        create = findViewById(R.id.create);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*to perform login based on email password*/
                loginuser();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(com.trishasofttech.authentication.MysqlPhpApi.LoginActivity.this,
                        RegisterActivity.class);
                startActivity(register);
            }
        });

    }

    private void loginuser() {
        StringRequest stringRequest = new StringRequest(1, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (response.equalsIgnoreCase("success"))
                        {
                            Intent  intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                       else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("emailid", email.getText().toString());
                map.put("passwordid", password.getText().toString());
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
