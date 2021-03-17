package utic.app_sistemacorp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Login extends AppCompatActivity
{
    EditText et1,et2;
    private Cursor fila;

    ////
    private SQLiteDatabase database;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     }

    public void login(View v)
    {
        et1= (EditText) findViewById(R.id.usuario);
        et2= (EditText) findViewById(R.id.password);

        String usuario=et1.getText().toString();
        String contrasena=et2.getText().toString();

        ////
        Database database = new Database(this);

        fila =database.getReadableDatabase().rawQuery("select user,contrasena,nombre from usuarios where user='"+ usuario +"' and contrasena='"+contrasena+"'", null);

        if (fila.moveToFirst())
        {
               //cierra activity Login
               finish();

                Intent i = new Intent(Login.this, MainActivity.class);
                i.putExtra("logueado",true);
               startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Usuario/Password No Validos",Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.usuario)).setText(""); ((EditText)findViewById(R.id.password)).setText("");
        }
        ////
    }

    public void msalir(View v)
    {
        finish();
    }
}