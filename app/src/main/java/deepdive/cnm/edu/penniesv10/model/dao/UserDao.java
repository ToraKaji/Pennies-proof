package deepdive.cnm.edu.penniesv10.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import deepdive.cnm.edu.penniesv10.model.entities.User;
import java.util.List;

@Dao
public interface UserDao {

  @Query("SELECT * FROM User WHERE user_id=:userId")
  User select(long userId);

  @Query("SELECT * FROM User ORDER BY user_id")
  List<User> select();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(User user);
}
