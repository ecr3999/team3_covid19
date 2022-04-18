package com.example.team3_covid19;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    TextView tvUsername, tvPassword;
    Button btnLogin;
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
        if(username.equals("user")&&password.equals("user")){
            startStoreSession();
            Intent intent= new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this,"Username dan Password tidak match!!",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void startStoreSession(){
        SessionManagement.getInstance().startUserSession(LoginActivity.this, 15);
    }
}
