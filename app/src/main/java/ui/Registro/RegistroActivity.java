package ui.Registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginsharedpreference.R;

import modelo.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private EditText etdni,etnombre,etapellido,etemail,etpass;
    private Button guardar;
    private RegistroViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        iniciarVista();
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        vm.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etdni.setText(usuario.getDni()+"");
                etnombre.setText(usuario.getNombre());
                etapellido.setText(usuario.getApellido());
                etemail.setText(usuario.getEmail());
                etpass.setText(usuario.getPass());
            }
        });
        vm.cargarDatos(getApplicationContext());
    }
    public void iniciarVista(){
        etdni=findViewById(R.id.etDniRegistro);
        etnombre=findViewById(R.id.etNombreRegistro);
        etapellido=findViewById(R.id.etApellidoRegistro);
        etemail=findViewById(R.id.etEmailRegsitro);
        etpass=findViewById(R.id.etPassRegistro);
        guardar=findViewById(R.id.btGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long dni=Long.parseLong(etdni.getText().toString());
               String nombre= etnombre.getText().toString();
                String apellido =etapellido.getText().toString();
               String email= etemail.getText().toString();
                String pass=etpass.getText().toString();
               Usuario usuario=new Usuario(dni,nombre,apellido,email,pass);
               vm.guardarDatos(getApplicationContext(),usuario);
            }
        });
    }
}
