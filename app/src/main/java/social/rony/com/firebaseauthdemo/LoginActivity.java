package social.rony.com.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail;
    EditText editTextPassword;
    Button btnRegister;
    ProgressDialog progressDialog;
    String email;
    String password;
    FirebaseAuth firebaseAuth;
    TextView textViewRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.edittextemail);
        editTextPassword = (EditText) findViewById(R.id.edittextpassword);
        textViewRegistrarse = ( TextView) findViewById(R.id.textviewRegistrarse);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
        textViewRegistrarse.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            //Profile
            finish();
            startActivity(new Intent( getApplicationContext(), ProfileActitivy.class));
        }
    }

    @Override
    public void onClick(View v) {

        if(v == textViewRegistrarse)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }
        if(v == btnRegister)
        {

            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();

            if(email.isEmpty())
            {
                Toast.makeText(this,"Debe ingresar su correo electrónico",Toast.LENGTH_SHORT).show();
                return;

            }
            if(password.isEmpty())
            {
                Toast.makeText(this,"Debe ingresar su contraseña",Toast.LENGTH_SHORT).show();
                return;

            }

            progressDialog.setMessage("Verificando usuario...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,"Usuario Validado perfectamente",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent( getApplicationContext(), ProfileActitivy.class));
                        return;
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Usuario o Contraseña incorrecto",Toast.LENGTH_LONG).show();
                        return;
                    }

                }
            });
        }

    }
}
