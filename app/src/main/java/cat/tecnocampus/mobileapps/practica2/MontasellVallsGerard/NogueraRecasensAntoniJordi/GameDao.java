package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDao {
    @Query("Select * FROM Game ")
    List<Game> getAll();

    @Insert
    void insert(Game game);

    @Delete
    void delete(Game game);

    @Query("Select * FROM Game WHERE player_nick_name LIKE :nickName ")
    List<Game> gatAllGamesByNick(String nickName);
}
