package com.example.sunateraho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class logintabfragment extends Fragment {

    EditText email,password;
    TextView forgetpassword;
    ProgressDialog progressDialog;

    FirebaseAuth mAuthh;
    FirebaseUser mUserr;
    Button login,phonesignup;

    float v = 0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.pass);
        forgetpassword = root.findViewById(R.id.forgetpassword);
        login = root.findViewById(R.id.loginbutton);
        mAuthh = FirebaseAuth.getInstance();
        mUserr = mAuthh.getCurrentUser();
        phonesignup = root.findViewById(R.id.phonesignup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

                }

        });

        phonesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),otpscreen.class);
                startActivity(intent);

            }

        });






        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetpassword.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetpassword.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();








        return root;
    }






    private void performLogin() {


        if(email.getText().toString().isEmpty()) {
            email.setError("enter email address");
        }

        else if (email.getText().toString().trim().matches(emailPattern)) {

        }

        else {
            email.setError("Invalid email address");
        }


        if(password.getText().toString().isEmpty() || password.length()<6){
            password.setError("Enter Proper Password");
        }


        else {



            mAuthh.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    {
                        if (task.isSuccessful())
                        {
                            sendUserToNextActivity();
                            Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
                        }

                        else
                        {

                            Toast.makeText(getActivity(),"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }

    }

    private void sendUserToNextActivity() {

        Intent intent=new Intent(getActivity(),dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
