package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button signup,login;
    EditText name, email,pass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup= findViewById(R.id.buttonsignup);
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        login=findViewById(R.id.login);

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        signup.setOnClickListener(view -> {
            String mail=email.getText().toString();
            String password=pass.getText().toString();

            if(password.length()<6){
                Toast.makeText(RegisterActivity.this,"Şifre en az 6 karakter olmalıdır.",Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Kayıt başarılı",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Kayıt başarısız",Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}