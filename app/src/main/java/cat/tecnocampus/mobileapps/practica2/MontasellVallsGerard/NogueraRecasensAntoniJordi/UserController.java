package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import android.app.Application;

import androidx.room.Room;

import java.util.List;

public class UserController {
    private static  UserController userController ;
    private UserDao userDao;


    public UserController(Application application){
        AppDatabase db = Room.databaseBuilder(application,AppDatabase.class,"database-name")
                .allowMainThreadQueries().build();
        userDao=db.userDao();

    }

    public void insertUser(User user){userDao.insertAll(user);}
    public List<User> listUser(){return userDao.getAll();}

}
