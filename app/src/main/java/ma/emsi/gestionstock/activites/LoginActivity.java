package ma.emsi.gestionstock.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ma.emsi.gestionstock.R;
import ma.emsi.gestionstock.api.RetrofitClient;
import ma.emsi.gestionstock.model.LoginResponse;
import ma.emsi.gestionstock.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private ArrayList userslist;
    private List<LoginResponse> logresp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }


    private void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 4) {
            editTextPassword.setError("Password should be atleast 4 character long");
            editTextPassword.requestFocus();
            return;
        }

        Call<LoginResponse[]> call = RetrofitClient.getInstance().getApi().userLogin();
        call.enqueue(new Callback<LoginResponse[]>() {
            @Override
            public void onResponse(Call<LoginResponse[]> call, Response<LoginResponse[]> response) {
                //response.body();
                logresp = Arrays.asList(response.body());

                for (LoginResponse lg:logresp) {
                          if(lg.getUsername().equals(email) && lg.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this,"welcome "+lg.getUsername(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                          }
                }
                Toast.makeText(LoginActivity.this,"bad login",Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<LoginResponse[]> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"server 404",Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}
