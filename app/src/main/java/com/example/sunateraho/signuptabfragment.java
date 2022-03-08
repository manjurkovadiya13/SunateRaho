package com.example.sunateraho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signuptabfragment extends Fragment {

EditText emailforsignup,pass_signup,confirmpass;
Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

float v= 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment,container,false);



        emailforsignup = root.findViewById(R.id.emailsignup);
        pass_signup = root.findViewById(R.id.pass_signup);

        signup = root.findViewById(R.id.signupp);
        confirmpass = root.findViewById(R.id.confirmpass);
        progressDialog=new ProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }


        });


        emailforsignup.setTranslationX(800);
        pass_signup.setTranslationX(800);

        signup.setTranslationX(800);
        confirmpass.setTranslationX(800);

        emailforsignup.setAlpha(v);
        pass_signup.setAlpha(v);

        signup.setAlpha(v);
        confirmpass.setAlpha(v);

        emailforsignup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass_signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();

        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirmpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();



        return root;


    }

    private void PerformAuth() {


        if(emailforsignup.getText().toString().isEmpty()) {
            emailforsignup.setError("enter email address");
        }

           else if (emailforsignup.getText().toString().trim().matches(emailPattern)) {

            }

           else {
                emailforsignup.setError("Invalid email address");
            }


            if(pass_signup.getText().toString().isEmpty() || pass_signup.length()<6){
               pass_signup.setError("Enter Proper Password");
           }


        else {

            progressDialog.setMessage("Please Wait While Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(emailforsignup.getText().toString(),pass_signup.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    {
                          if (task.isSuccessful())
                          {
                              progressDialog.dismiss();
                              sendUserToNextActivity();
                              Toast.makeText(getActivity(),"Registration",Toast.LENGTH_SHORT).show();
                              emailforsignup.getText().clear();
                              pass_signup.getText().clear();
                              confirmpass.getText().clear();

                          }

                          else
                          {
                              progressDialog.dismiss();
                              Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();
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
