package utic.app_sistemacorp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Modelo
{
    public Integer codigo;
    public String descripcion;
    public String nombre;
    public String login;
    public String clave;
    public String usuario;

    public Integer precio;
    public Integer id_grupo;
    public Integer id_marca;

    private SQLiteDatabase database;
    private Database dbHelper;

    public List<String> lista;

    //para todas las tablas con 2 atributos
    public static String[] colsDefault={"codigo","descripcion"};

    ///estructura de la tabla
    public static String[] colsArticulos={"codigo","descripcion","precio","gru_cod","mar_cod"};

    public static String[] colsPat={"codigo","nombre","user","contrasena"};

    public Modelo(Context context) { dbHelper = new Database(context); }
    public void open() throws SQLException {  database = dbHelper.getWritableDatabase(); }
    public void close() { dbHelper.close(); }

    public void buscarDefaultMod(String tbl, String codigo, String[] cols, int i, boolean conParam)
    {
        Cursor c = null;
        if(conParam)
        {
            c = database.query(tbl,cols,"codigo=?",new String[]{codigo}, null, null, null);
            if (c.moveToFirst()) {
                do {
                    if(i == 1)//todas la tablas con 2 columnas
                    {
                        this.codigo = c.getInt(0);
                        this.descripcion = c.getString(1);
                    }
                    else if(i == 2)//caso usuario
                    {
                        this.codigo = c.getInt(0);
                        this.nombre = c.getString(1);
                        this.login = c.getString(2);
                        this.clave = c.getString(3);
                    }else if(i == 4)//caso articulo
                    {
                        this.codigo = c.getInt(0);
                        this.descripcion = c.getString(1);
                        this.precio = c.getInt(2);
                        this.id_grupo = c.getInt(3);
                        this.id_marca = c.getInt(4);
                    }
                } while(c.moveToNext());
            }
        }else{
            c = database.query(tbl,cols,null,null,null,null," codigo ASC ");
            c.moveToFirst();
            lista = new ArrayList<>();
            while (!c.isAfterLast())
            {
                lista.add(c.getInt(0)+"-"+c.getString(1));
                c.moveToNext();
            }
        }
        c.close();
    }//FIN BUSCAR DEFAULT

    public static int getIndex(Spinner spinner, Integer valor)
    {
        int index = 0;
        for (int i=0; i< spinner.getCount(); i++){
            if(Integer.valueOf(spinner.getItemAtPosition(i).toString().split("-")[0]).compareTo(valor) == 0){
                index = i;
            }
        }
        return index;
    }

    public int getItemPositionById(String tbl, String codigo)
    {
        int posit = 0;
        Cursor cu = database.query(tbl,new String[]{"codigo"},null,null,null,null,null);
        for(cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()){
            if (cu.getInt(cu.getColumnIndex("codigo")) == Integer.valueOf(codigo)){
                posit = cu.getPosition();
            }
        }
        cu.close();
        return posit;
    }

    public void crearOActualizarDefaultMod(String tbl, String codigo, String nombre)
    {
        ContentValues valores = new ContentValues();
        valores.put("descripcion", nombre);
        if(codigo != null)
        {
            database.update(tbl,valores," codigo =?",new String[]{String.valueOf(codigo)});
        }else{
            database.insert(tbl, null, valores);
        }
    }

    public void crearOActualizarPatrullero(String tbl, String codigo, String nombre, String login, String clave)
    {
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("user",login);
        valores.put("contrasena",clave);
        if(codigo != null)
        {
            database.update(tbl,valores,"codigo =?",new String[]{String.valueOf(codigo)});
        }else
        {
            database.insert(tbl, null, valores);
        }
    }


    public void crearOActualizarArticulos(String tbl, String codigo, String descri, String precio, String idgrupo, String idmarca)
    {
        ContentValues valores = new ContentValues();
        valores.put("descripcion", descri);
        valores.put("precio", precio);
        valores.put("gru_cod", idgrupo.split("-")[0]);
        valores.put("mar_cod", idmarca.split("-")[0]);

        if(codigo != null)
        {
            database.update(tbl,valores,"codigo =?",new String[]{String.valueOf(codigo)});
        }else{
            database.insert(tbl, null, valores);
        }
    }//Fin actualizar articulos

    public void eliminar(String tbl, Integer id)
    {
        database.delete(tbl,"codigo ="+id,null);
    }
    public void dataMod(int i, Cursor c){}
}
