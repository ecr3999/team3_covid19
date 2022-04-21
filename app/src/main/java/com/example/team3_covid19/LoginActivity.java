package com.example.team3_covid19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_DATA = "com.example.team3_covid19.LoginActivity.LOGIN_DATA";

    TextView tvUsername, tvPassword;
    Button btnLogin;
    LoginData loginData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvUsername = findViewById(R.id.editTxtUsername);
        tvPassword = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"Username dan Password tidak boleh kosong!!",Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();
        RetrofitLoginData retrofitLoginData = new RetrofitLoginData();
        retrofitLoginData.getAPI().getLoginData(requestBody).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                loginData = response.body();
                if(loginData == null)
                {
                    Toast.makeText(LoginActivity.this,"Logon Server is down",Toast.LENGTH_SHORT).show();
                    return;
                }

                System.out.println(response.body().toString());

                if(loginData.getStatus()){

                    startStoreSession();
                    storeUserData(LoginActivity.this, response.body().getData());
                    Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Username atau Password salah!!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.e("Failure:", t.getMessage());
            }
        });
    }

    private void startStoreSession(){
        SessionManagement.getInstance().startUserSession(LoginActivity.this, 15);
    }

    public void storeUserData(Context context, UserData userData){
        SharedPreferences sharedPref = context.getSharedPreferences(LOGIN_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPref.edit();
        editor.putString("username", userData.getUsername());
        editor.putString("full_name", userData.getFullName());
        editor.putString("avatar", userData.getAvatar());
        editor.putString("email", userData.getEmail());
        editor.commit();
    }
}

