package sv.com.edu.udb.dsm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sv.com.udb.dsm.R;

public class HomeActivity extends AppCompatActivity {


    //aqui esta la parte que agrege para lo de patron de diseño Singleton
    private static HomeActivity instanciaUnica;

    private synchronized static void createInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new HomeActivity();
        }
    }

    public static HomeActivity getInstance() {
        createInstance();

        return instanciaUnica;
    }

    public void printName() {
        System.out.println("Marco Antonio Hernandez");
    }

    //aqui finaliza la parte de el patron de diseño Singleton

    public static final String USER = "user";
    public static final String NO_HAY_USUARIO = "No hay usuario";
    public static final String CREDENCIALES = "credenciales";
    private TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        getUser();
    }

    private void getUser(){
        SharedPreferences preferences = getSharedPreferences(CREDENCIALES, Context.MODE_PRIVATE);
        String user = preferences.getString(USER, NO_HAY_USUARIO);
    }


    public void onClick(View view){
        switch(view.getId()){
            case R.id.ejercicio2:
                viewExercise2();
                break;
        }
    }

    private void viewExercise2(){
        Intent i = new Intent(this, EcuacionActivity.class);
        startActivity(i);
    }







}