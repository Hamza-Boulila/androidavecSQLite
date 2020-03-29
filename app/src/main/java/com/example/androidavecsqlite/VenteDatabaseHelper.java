package com.example.androidavecsqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class VenteDatabaseHelper extends SQLiteOpenHelper {
    // Database and Table Info
    private static final String DATABASE_NAME = "Vente";
    private static final String TABLE_Client = "Client";
    // Client Table Columns
    private static final String COL_1 = "id_Cl";
    private static final String COL_2 = "Nom";
    private static final String COL_3 = "Ville";
    public VenteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // Ceci pour créer la BD
        SQLiteDatabase db = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_Client = "CREATE TABLE " + TABLE_Client +
                "(" +
                COL_1 + " INTEGER PRIMARY KEY," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT" +
                ")";
        db.execSQL(CREATE_TABLE_Client);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Client);
        onCreate(db);
    }
    // Insert a client into the database
    public void addClient(Client client) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, client.getId_Cl());
        values.put(COL_2, client.getNom());
        values.put(COL_3, client.getVille());
        db.insert(TABLE_Client, null, values);
        db.close();
    }
    public List<Client> getAllData() {
        List<Client> listeClient = new ArrayList<Client>();
        String CLIENTS_SELECT_QUERY = "SELECT * FROM " + TABLE_Client;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CLIENTS_SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                Client cl = new Client();
                cl.id_Cl =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_1)));
                cl.Nom = cursor.getString(cursor.getColumnIndex(COL_2));
                cl.Ville = cursor.getString(cursor.getColumnIndex(COL_3));
                listeClient.add(cl);
            } while(cursor.moveToNext());
        }
        db.close();
        return listeClient;
    }
    public void UpdateClient(Client client) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, client.getNom());
        values.put(COL_3, client.getVille());
        // Updating profile picture url for user with that userName
        db.update(TABLE_Client, values, COL_1 + " = ?",
                new String[] { String.valueOf(client.getId_Cl()) });
        db.close();
    }
    public void deleteClient(int id) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_Client,"id_Cl=?",new String[]{String.valueOf(id)});
        //db.execSQL("Delete From " + TABLE_Client + " Where id_Cl = " + String.valueOf(id));
        db.close();
    }
    public void EmptyClient()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Delete From " + TABLE_Client);
        //db.delete(TABLE_Client, null, null);
        db.close();
    }
}
