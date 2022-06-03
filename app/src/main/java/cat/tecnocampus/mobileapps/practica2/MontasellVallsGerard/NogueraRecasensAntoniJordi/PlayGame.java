package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class PlayGame extends AppCompatActivity {


    private TextView guessingLetter;
    private TextView actualWord;
    private String letterToGuess;
    private String sToGuess;
    private String sActWord = "PALABRA";
    RequestQueue queue;
    private String url="https://random-word-api.herokuapp.com/word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        queue = Volley.newRequestQueue(getApplicationContext());
        getStringRequest();

        guessingLetter = (EditText) findViewById(R.id.et_Letter);
        actualWord = (TextView) findViewById(R.id.tv_Answer);

        setActualWordTextView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_rank, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_rank:
                Intent intent =new Intent(this,Ranking.class);
                startActivity(intent);
                break;
        }
        return (super.onOptionsItemSelected(item));
    }


    private void setActualWordTextView() {
        for(char ch : sActWord.toCharArray()) {
            String s = actualWord.getText().toString();
            s += "_";
            actualWord.setText(s);
        }
    }

    private void checkActualWord() {
        if(sActWord.toUpperCase(Locale.ROOT).contains(letterToGuess.toUpperCase())) {
            char guessLetter = Character.toUpperCase(letterToGuess.charAt(0));
            String s = actualWord.getText().toString();
            StringBuilder sb = new StringBuilder(s);
            int actualPosition = 0;
            for(char ch : sActWord.toCharArray()) {
                ch = Character.toUpperCase(ch);
                if(ch == guessLetter) { sb.setCharAt(actualPosition, guessLetter); }
                s = sb.toString();
                actualWord.setText(s);
                actualPosition++;
            }
        }
    }


    public void checkLetterOnClick(View view) {
        letterToGuess = guessingLetter.getText().toString();
        checkActualWord();
    }

    public void getStringRequest() {
        Log.v("Test", "response");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sActWord=response.substring(2,response.length()-2);
                Log.v("Test",sActWord);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Test", "Error al StringRequest");
            }
        });
        queue.add(stringRequest);
    }

    private void saveGame(int punctuation){
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nick-name");
        Game game = new Game(nickName,sActWord,punctuation);
        UserController userController = UserController.getUserController();
        userController.insertGame(game);
    }
}