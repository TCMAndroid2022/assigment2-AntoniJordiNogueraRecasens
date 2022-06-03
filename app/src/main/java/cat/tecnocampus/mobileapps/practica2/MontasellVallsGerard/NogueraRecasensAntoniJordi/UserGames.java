package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserGames extends AppCompatActivity {


    RecyclerView rv_UsersGames;
   private  rv_GamesAdapter rv_Adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Game> dataSet;
    UserController userController;
    String nickName;
    private void createDummy(){
        dataSet=new ArrayList<>();
        dataSet.add(new Game("Gerahrdmv","palabra",15));
        dataSet.add(new Game("Gerahrdmv","word",13));
        List<Game> games= userController.getAllGamesByUser(nickName);
        for(Game game :  games){
            dataSet.add(game);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_games);
        userController= userController.getUserController();

        Intent intent = getIntent();
        nickName = intent.getStringExtra("nick-name");

        createDummy();

        rv_UsersGames = findViewById(R.id.rv_Plays);
        for(Game game : dataSet){
            Log.e("Nick",game.getPlayerNickName());
        }
        rv_Adapter = new rv_GamesAdapter(dataSet);
        if(rv_Adapter==null)Log.e("Test","Fallo es null");
        rv_UsersGames.setAdapter(rv_Adapter);

        layoutManager = new GridLayoutManager(this,1);
        rv_UsersGames.setLayoutManager(layoutManager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}