package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity implements rv_RankAdapter.ItemClickListener{


    private RecyclerView rv_PlayersRanking;
    private rv_RankAdapter rv_Adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<User> dataSet;
    UserController userController;

    private void createDummy(){
        dataSet=new ArrayList<>();
        dataSet.add(new User("Faker",100,12));
        dataSet.add(new User("Gerahrdmv",150,15));
        List<User> users= userController.getAllUsers();
        for(User user :  users){
            dataSet.add(user);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        userController= userController.getUserController();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createDummy();

        rv_PlayersRanking = findViewById(R.id.rv_PlayersRanking);

        rv_Adapter = new rv_RankAdapter(dataSet,R.layout.row_rank);
        rv_PlayersRanking.setAdapter(rv_Adapter);
        rv_Adapter.setClickListener(this);

        layoutManager = new GridLayoutManager(this,1);
        rv_PlayersRanking.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View view, int position) {
        final User actual = dataSet.get(position);
        Intent intent = new Intent(this, UserGames.class);
        intent.putExtra("nick-name", actual.getNickName());
        startActivity(intent);
    }
}