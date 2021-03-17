package utic.app_sistemacorp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    boolean logueado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_mimenu);// menu con navegador
        if(getIntent().getExtras() != null)
        {
            logueado=getIntent().getExtras().getBoolean("logueado");
        }
    }

    public void marticulo(View v)
    {
        Intent i = new Intent(MainActivity.this, Controlador.class);
        i.putExtra("tabla","articulos");
        startActivity(i);
    }

    public void mgrupo(View v)
    {
        Intent i = new Intent(MainActivity.this,Controlador.class);
        i.putExtra("tabla","grupo");
        startActivity(i);
    }


    public void mmarca(View v)
    {
        Intent i = new Intent(MainActivity.this, Controlador.class);
        i.putExtra("tabla","marcas");
        startActivity(i);
    }

    public void musuarios(View v)
    {
        Intent i = new Intent(MainActivity.this, Controlador.class);
        i.putExtra("tabla","usuarios");
        startActivity(i);
    }


    public void msalir(View v)
    {
        finish();
    }
}
