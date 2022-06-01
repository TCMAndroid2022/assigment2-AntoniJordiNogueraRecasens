package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {


    RecyclerView rv_PlayersRanking;
    RecycleViewAdapter rv_Adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<User> dataSet;

    private void createDummy(){
        dataSet=new ArrayList<>();
        dataSet.add(new User("Faker",100,12));
        dataSet.add(new User("Gerahrdmv",150,15));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //TODO: RecyclerView
        rv_PlayersRanking = findViewById(R.id.rv_PlayersRanking);

        createDummy();
        rv_Adapter = new RecycleViewAdapter(dataSet);
        rv_PlayersRanking.setAdapter(rv_Adapter);

        layoutManager = new GridLayoutManager(this,2);
        rv_PlayersRanking.setLayoutManager(layoutManager);

    }
}