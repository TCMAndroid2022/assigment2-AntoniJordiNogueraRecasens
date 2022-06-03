package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity
public class Game {
    @PrimaryKey
    int id;

    @ColumnInfo(name="player_nick_name")
    String playerNickName;

    String word;

    int punctuation;

    public Game(String playerNickName, String word, int punctuation) {
        this.id = new Random().nextInt(99999);;
        this.playerNickName = playerNickName;
        this.word = word;
        this.punctuation = punctuation;
    }

    public int getId() {
        return id;
    }

    public String getPlayerNickName() {
        return playerNickName;
    }

    public String getWord() {
        return word;
    }

    public int getPunctuation() {
        return punctuation;
    }
}
