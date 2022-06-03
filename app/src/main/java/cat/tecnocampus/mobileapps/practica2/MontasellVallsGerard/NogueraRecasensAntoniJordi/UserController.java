package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import android.app.Application;

import androidx.room.Room;

import java.util.List;

public class UserController {
    private static  UserController userController ;
    private UserDao userDao;
    private GameDao gameDao;

    public static UserController getUserController() {
        return userController;
    }

    public UserController(Application application){
        AppDatabase db = Room.databaseBuilder(application,AppDatabase.class,"database-name")
                .allowMainThreadQueries().build();
        userDao=db.userDao();
        gameDao=db.gameDao();
        userController = this;
    }

    public void insertUser(User user){userDao.insert(user);}

    public void insertGame(Game game){gameDao.insert(game);}

    public List<User> listUser(){return userDao.getAll();}

    public List<String> getAllNicks() {return userDao.getAllNicks();}

    public List<User> getAllUsers() {return userDao.getAll();}

    public List<Game> getAllGamesByUser(String nickName) {return gameDao.gatAllGamesByNick(nickName);}
}
