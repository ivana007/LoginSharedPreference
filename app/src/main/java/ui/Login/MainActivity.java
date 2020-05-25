package ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginsharedpreference.R;

public class MainActivity extends AppCompatActivity {
private EditText etmail,etpass;
private Button btlogin,btregistro;
private TextView carteltv;
private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarVista();
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getCartelMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                carteltv.setText(s);
            }
        });
    }
    public void iniciarVista(){
        etmail=findViewById(R.id.etUsuario);
        etpass=findViewById(R.id.etContraseña);
        carteltv=findViewById(R.id.tvcartel);
        btlogin=findViewById(R.id.btLogin);
        btregistro=findViewById(R.id.btRegistrar);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    vm.validarDatos(getApplicationContext(),etmail.getText().toString(),etpass.getText().toString());
            }
        });
        btregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrarDatos(getApplicationContext());
            }
        });
    }
}
