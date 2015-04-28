package com.dyck.tim.middle.earth.index.data;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.dyck.tim.middle.earth.index/databases/";

    private static String DB_NAME = "meiDb";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //myDataBase.update("MiddleEarthIndex", null, null, null);
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database doesn't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        if (myDataBase != null && myDataBase.isOpen())
            myDataBase.close();
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MiddleEarthIndex");
        onCreate(db);
    }

    public Cursor getColumn(String type, String parentType) {
        String query = "SELECT DISTINCT Name, Description FROM MiddleEarthIndex";

        if (parentType != "")
            query += " WHERE Parent_Type = '" + parentType + "'";

        if (!type.equals("") && !type.equals("All") && !type.equals("Miscellaneous: All") && !type.equals("Places: All") && !type.equals("Races: All"))
            query += " AND Type = '" + type + "'";

        query += " ORDER BY [Name]";

        Cursor c = myDataBase.rawQuery(query, null);
        return c;
    }

    public Cursor searchResult(String searchKey) {
        //Opposite of elegant.  Could figure out collations one day
        String query = "SELECT DISTINCT Name, Description FROM MiddleEarthIndex WHERE replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace( lower(Name), 'à','a'), 'á','a'), 'â','a'), 'ä', 'a'), 'è','e'), 'é', 'e'), 'ê','e'), 'ë','i'),'ó','o') ,'ô','o') ,'ö','o'),'ú','u'), 'û', 'u'), 'ç','c') LIKE '%" +
                searchKey +
                "%'";

        Cursor c = myDataBase.rawQuery(query, null);
        return c;
    }

}
