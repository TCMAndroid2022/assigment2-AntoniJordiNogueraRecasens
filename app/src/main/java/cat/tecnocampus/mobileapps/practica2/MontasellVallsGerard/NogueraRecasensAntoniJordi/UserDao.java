package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user ORDER BY punctuation DESC")
    List<User> getAll();

    @Query("SELECT nick_name FROM user ")
    List<String> getAllNicks();

    @Query("SELECT * FROM user WHERE nick_name LIKE :nick")
    User findByNick(String nick);

    @Update()
    void updateUser(User user);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);

    @Delete
    void delete(User user);

}
