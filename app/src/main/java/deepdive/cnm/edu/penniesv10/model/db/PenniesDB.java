package deepdive.cnm.edu.penniesv10.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;
import deepdive.cnm.edu.penniesv10.model.dao.UserDao;
import deepdive.cnm.edu.penniesv10.model.entities.User;
import java.util.Date;

@Database(
    entities = {User.class},
    version = 1

)
public abstract class PenniesDB extends RoomDatabase {

  //Creates a static instance of PenniesDB and sets it to null
  public static PenniesDB instance = null;
  public static String DB_NAME = "pennies_db";



  //Allows for dao access
  public abstract UserDao getUserDao();



  //Creates an instance of database if null or returns the known database instance
  public synchronized static void forgetInstance() {
    instance = null;
  }

  public synchronized static PenniesDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), PenniesDB.class, DB_NAME)
          .build();
    }
    return instance;
  }

  //Creates type converters for Date objects passed into the database
  public static class Converters {

    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }
}
