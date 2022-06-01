package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {


    RecyclerView rv_PlayersRanking;
    RecycleViewAdapter rv_Adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<User> dataSet;
    UserController userController;

    private void createDummy(){
        dataSet=new ArrayList<>();
        dataSet.add(new User("Faker",100,12));
        dataSet.add(new User("Gerahrdmv",150,15));
        List<User> users= userController.getAll();
        for(User user :  users){
            dataSet.add(user);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        userController= new UserController(getApplication());
        createDummy();

        rv_PlayersRanking = findViewById(R.id.rv_PlayersRanking);

        rv_Adapter = new RecycleViewAdapter(dataSet);
        rv_PlayersRanking.setAdapter(rv_Adapter);

        layoutManager = new GridLayoutManager(this,1);
        rv_PlayersRanking.setLayoutManager(layoutManager);

    }
}