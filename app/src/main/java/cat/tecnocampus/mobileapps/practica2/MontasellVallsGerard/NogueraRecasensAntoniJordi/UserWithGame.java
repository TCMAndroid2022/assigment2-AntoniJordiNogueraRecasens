package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithGame {
    @Embedded public User user;
    @Relation(
            parentColumn = "nick_name",
            entityColumn = "player_nick_name"
    )
    public List<Game> games;
}
