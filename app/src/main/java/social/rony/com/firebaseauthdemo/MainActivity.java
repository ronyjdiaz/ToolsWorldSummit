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

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText editTextEmail;
    EditText editTextPassword;
    TextView textviewLogin;
    Button btnRegister;
    ProgressDialog progressDialog;
    String email;
    String password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.edittextemail);
        editTextPassword = (EditText) findViewById(R.id.edittextpassword);
        textviewLogin = (TextView) findViewById(R.id.textviewLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
        textviewLogin.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {

        if (v == btnRegister)
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

            progressDialog.setMessage("Registrando usuario...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                    this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful())
                            {
                                //Toast.makeText(MainActivity.this,"Usuario registrado satisfactoriamente",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent( getApplicationContext(), ProfileActitivy.class));
                                return;
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Falla en registro de usuario, intente nuevamente",Toast.LENGTH_SHORT).show();

                                return;
                            }

                        }
                    }

            );




        }

        if(v == textviewLogin)
        {

            startActivity(new Intent(this,LoginActivity.class));
        }


    }
}
