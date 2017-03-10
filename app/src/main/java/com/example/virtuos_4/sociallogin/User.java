package com.example.virtuos_4.sociallogin;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.JsonRequest;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends AppCompatActivity {
    private static final String host = "api.linkedin.com";
    private static final String topCardUrl = "https://" + host
            + "/v1/people/~:(id,first-name,last-name,public-profile-url,picture-url,email-address,picture-urls::(original))";
    TextView email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        APIHelper apiHelpe1r = APIHelper.getInstance(getApplicationContext());
        apiHelpe1r.getRequest(User.this, topCardUrl, new ApiListener() {
                    @Override
                    public void onApiSuccess(ApiResponse result) {
                        try {
                            Log.e("result", String.valueOf(result.getResponseDataAsJson()));
                            setText(result.getResponseDataAsJson());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onApiError(LIApiError error) {
                    }
                }

        );

        email=(TextView)findViewById(R.id.txtName);
        phone=(TextView)findViewById(R.id.txtEmail);

    }
    public void setText(JSONObject result) throws JSONException {

        email.setText(result.get("emailAddress").toString());
        phone.setText(result.get("publicProfileUrl").toString());
    }
    public void onBackPressed(){
        super.onBackPressed();
       this.finish();
    }
    public void onStop(){
        super.onStop();
        this.finish();
    }
}
