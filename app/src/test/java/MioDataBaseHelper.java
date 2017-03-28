import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MioDataBaseHelper extends SQLiteOpenHelper {

    public MioDataBaseHelper(Context context){

        super(context, "database.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE note(" +
                "_id INTEGER PRIMARY KEY," +
                "date INTEGER NOT NULL," +
                "title TEXT NOT NULL" +
                "body TEXT," +
                ");";
        db.execSQL(sql);

        /*sql= "INSERT INTO note VALUES(" +
                "null, +"+", Viaggi da fare, Londra, Parigi e Tokyo, aereo alle 15.00";
*/

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
