package in.example.martialartsql.model;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String COLOR_KEY = "color";
    private static final String PRICE_KEY = "price";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabseSQL = "create table " + MARTIAL_ARTS_TABLE +
                "( " +ID_KEY+ " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + PRICE_KEY + " real" +
                ", "+ COLOR_KEY + " text" + " )";

        db.execSQL(createDatabseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + MARTIAL_ARTS_TABLE);
        onCreate(db);

    }

    public void addMartialArt( MartialArt martialArt){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ARTS_TABLE +
                " values(null, '" + martialArt.getMartialArtName()
                + "', '" + martialArt.getMartialArtPrice() +
                "', '" + martialArt.getMartialArtColor() +
                "')";

        sqLiteDatabase.execSQL(addMartialArtSQLCommand);
        sqLiteDatabase.close();
    }
    public void deleteMartialArtObjectFromDatabaseByID(int id) {


        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + MARTIAL_ARTS_TABLE +
                " where " + ID_KEY + " = " + id;
        database.execSQL(deleteMartialArtSQLCommand);
        database.close();

    }
    public void modifyMartialArtObject(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String modifyMartialArtSQLCommand = "update "+ MARTIAL_ARTS_TABLE+
                " set " + NAME_KEY + " = '" + martialArtName +
                "', "+PRICE_KEY + " = '" + martialArtPrice+
                "', "+COLOR_KEY + " = '" + martialArtColor+
                "' "+ "where " + ID_KEY + " = " + martialArtID;

        sqLiteDatabase.execSQL(modifyMartialArtSQLCommand);
        sqLiteDatabase.close();

    }

    public ArrayList<MartialArt> returnAlMartialArtObject(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sqlQueryCommand = "select * from "+MARTIAL_ARTS_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQueryCommand, null);

        ArrayList<MartialArt> martialArts = new ArrayList<>();

        while (cursor.moveToNext()){
            MartialArt currentMartialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2), cursor.getString(3));

            martialArts.add(currentMartialArtObject);
        }

        sqLiteDatabase.close();
        return martialArts;

    }

    public MartialArt returnMartialArtObjectById( int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sqlQueryCommand = "select * from "+MARTIAL_ARTS_TABLE +
                " where "+ ID_KEY +" = " +id;

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQueryCommand, null);
        MartialArt currentMartialArtObject = null;

        if (cursor.moveToFirst()){
            currentMartialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));

            sqLiteDatabase.close();
        }

        return currentMartialArtObject;
    }
}
