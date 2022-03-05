package com.example.turbovenom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity
{
    private EditText etUsername,etPassword,etConfirmPassword;
    private FirebaseAuth auth;

    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername=findViewById(R.id.etusernameSignup);
        etPassword=findViewById(R.id.etPasswordSignup);
        etConfirmPassword=findViewById(R.id.etconfirmPasswordSignup);
    }

    public void signup(View view){
        // TODO: 1- Get data from screen
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        String confirmPassword=etConfirmPassword.getText().toString();

        // TODO: 2- Data validation
        if(username.trim().isEmpty()||password.trim().isEmpty()
                ||confirmPassword.trim().isEmpty()){
            Toast.makeText(this,"Username or password is missing!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.equals(confirmPassword)){
            Toast.makeText(this,"Passwords do not match!!",Toast.LENGTH_SHORT).show();
            return;
        }


        // TODO: 3- Check username and password with Firebase Authentication
        auth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            // TODO: commands if successful
                            Intent i = new Intent(SignupActivity.this, AllPartsActivity.class);
                            startActivity(i);
                        }else{


                            Toast.makeText(SignupActivity.this,"Username or password is empty!",Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });

        //TODO: verify email format
        //check if email starts with letter or_

    }


    public boolean verifyEmail(AppCompatActivity activity,String email){
        int count1=0;
        String[]splitstring=email.split("@");
        if(splitstring.length!=2){
            Toast.makeText(activity,"Username or password are incorrect",Toast.LENGTH_SHORT).show();
            return false;
        }

        String username=splitstring[0].toLowerCase();
        String domain=splitstring[1].toLowerCase();

        if(username.trim().charAt(0)< 'A'&&username.trim().charAt(0)>'Z'){

            if(username.trim().charAt(0)=='_'&&username.trim().length()< 50){
                if(domain.trim().charAt(0)< 'A'&&domain.trim().charAt(0)>'Z'){
                    if(domain.trim().charAt(0)=='_'&&domain.trim().length()< 50){
                        for(int i=0;i<domain.length();i++){
                            if(domain.charAt(i)=='.')
                                count1++;
                        }
                        if(count1>1&&count1< 3){
                            if(domain.charAt(domain.length()-1)>='A'&&domain.charAt(domain.length()-1)<='Z')
                                return true;

                        }
                    }
                }
            }
            return false;
        }
        if(domain.trim().charAt(0)< 'A'&&domain.trim().charAt(0)>'Z'){
            if(domain.trim().charAt(0)=='_'&&domain.trim().length()< 50){
                for(int i=0;i<domain.length();i++){
                    if(domain.charAt(i)=='.')
                        count1++;
                }
                if(count1>1&&count1< 3){
                    if(domain.charAt(domain.length()-1)>='A'&&domain.charAt(domain.length()-1)<='Z')
                        return true;

                }
            }
        }
        return false;
    }

    public boolean VerifyPassword(String password)
    {
        if(password.trim().length()<8||password.trim().length()>30||password.trim().isEmpty())
            return false;
        int countCapital=0;
        int countSmall=0;
        int countWild=0;
        int countNum=0;
        char[]passwordArray=password.toCharArray();
        for(int i=0;i<password.trim().length();i++)
        {
            if(passwordArray[i]>='a'&&passwordArray[i]<='z')
                countSmall++;
            else if(passwordArray[i]>='A'&&passwordArray[i]<='Z')
                countCapital++;
            else if(passwordArray[i]>='0'&&passwordArray[i]>='9')
                countNum++;
            else countWild++;

        }
        if(countCapital==0||countSmall==0||countWild==0||countNum==0)return false;

        return true;

    }
}

