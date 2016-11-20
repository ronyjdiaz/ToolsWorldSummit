package social.rony.com.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ProfileActitivy extends AppCompatActivity implements View.OnClickListener{

    TextView textViewProfileEmail;
    Button btnLogout;
    Button btnGuardar;
    EditText editTextNombre;
    EditText editTextDireccion;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private  DatabaseReference databaseReferenceChange;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_actitivy);

        textViewProfileEmail = (TextView) findViewById(R.id.textViewProfileEmail);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnGuardar = (Button) findViewById(R.id.btnGuardarProfile);
        editTextNombre = (EditText) findViewById(R.id.edittextNombre    );
        editTextDireccion = (EditText) findViewById(R.id.edittextDireccion    );

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
      user = firebaseAuth.getCurrentUser();


        textViewProfileEmail.setText("Bienvenido " + user.getEmail());

        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        btnLogout.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        databaseReferenceChange = databaseReference.child(user.getUid());

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceChange.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String, Object> result = new HashMap<>();
                result = (HashMap<String, Object>) dataSnapshot.getValue();
                String nombre = result.get("nombre").toString();
                String direccion = result.get("direccion").toString();
                editTextNombre.setText(nombre);
                editTextDireccion.setText(direccion);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == btnLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));


        }
        if(v == btnGuardar)
        {
            String nombre = editTextNombre.getText().toString().trim();
            String direccion = editTextDireccion.getText().toString().trim();


            UserInformation userInformation  = new UserInformation(nombre,direccion);
            databaseReference.child(user.getUid()).setValue(userInformation);
            Toast.makeText(this,"Información guardada exitosamente...",Toast.LENGTH_SHORT).show();





        }

    }
}
