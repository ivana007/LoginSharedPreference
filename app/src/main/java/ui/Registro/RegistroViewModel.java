package ui.Registro;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import modelo.Usuario;
import request.ApiClient;

public class RegistroViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;


    public LiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData == null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public  void cargarDatos(Context context){
        Usuario usuario=ApiClient.leer(context);
            if(usuario.getDni()!=-1){
                usuarioMutableLiveData.setValue(usuario);
            }

    }


    public void guardarDatos(Context context, Usuario usuario){
        if(usuario!=null){
            ApiClient.guardar(context,usuario);
        }

    }

}
