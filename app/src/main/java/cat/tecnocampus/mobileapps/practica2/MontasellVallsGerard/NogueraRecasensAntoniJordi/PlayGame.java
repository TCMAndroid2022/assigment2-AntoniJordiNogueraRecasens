package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Locale;

public class PlayGame extends AppCompatActivity {


    private EditText guessingLetter;
    private TextView answerWord;
    private EditText guessingWord;

    private String lastLetter="";

    private String sToGuess;

    private String sActWord = "";
    private int nLettersUsed = 0;

    private UserController userController;
    RequestQueue queue;
    private String url="https://random-word-api.herokuapp.com/word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        userController = UserController.getUserController();

        guessingLetter = findViewById(R.id.et_Letter);
        answerWord = findViewById(R.id.tv_Answer);
        guessingWord = findViewById(R.id.et_Word);

        queue = Volley.newRequestQueue(getApplicationContext());
        getStringRequest();



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
        String s = answerWord.getText().toString();
        for(char ch : sActWord.toCharArray()) {
            s += "_";
        }
        answerWord.setText(s);
    }

    public void checkLetterOnClick(View view) {
        String letterToGuess = guessingLetter.getText().toString();
        if(lastLetter != letterToGuess){
            lastLetter = letterToGuess;
            nLettersUsed++;
            checkActualWord(letterToGuess);
        }
    }

    private void checkActualWord(String letterToGuess) {
        //Check if the word contains the letter
        if(sActWord.toUpperCase().contains(letterToGuess.toUpperCase())) {

            char guessLetter = Character.toUpperCase(letterToGuess.charAt(0));
            String s = answerWord.getText().toString();

            StringBuilder sb = new StringBuilder(s);
            int actualPosition = 0;

            for(char ch : sActWord.toCharArray()) {
                ch = Character.toUpperCase(ch);
                //Check if letter is in this position, true: change de _ for the letter
                if(ch == guessLetter) { sb.setCharAt(actualPosition, guessLetter); }
                //Parse it to string again and changed the text in screen
                s = sb.toString();
                answerWord.setText(s);
                //Prepare to check the next position
                actualPosition++;
            }
        }
        guessingLetter.setText("");
    }


    public void checkWordOnClick(View view) {
        float punctuation = 0;
        sToGuess = guessingWord.getText().toString();
        //Security when try to click when word is empty
        if(sToGuess.compareTo("")!=0) {
            //Check if the word and answer are the same
            if (sActWord.toUpperCase().equals(sToGuess.toUpperCase())) {
                // I have to do the calculations this way if I try to do them all at once it fails
                punctuation = ((sActWord.length() - nLettersUsed));
                punctuation = punctuation / sActWord.length();
                punctuation = punctuation * 10.0f;
                Toast.makeText(this, "CONGRATULATIONS, YOU OBTAINED " + punctuation + " POINTS", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "OOPS, SEEMS YOU HAVE NOT GUESSED IT, MORE LUCK NEXT TIME", Toast.LENGTH_LONG).show();
            }

            Log.v("PUNCTUATION", "Punctuation: " + punctuation);
            guessingWord.setText("");
            answerWord.setText("");
            saveGame(punctuation);
        }
    }

    public void getStringRequest() {
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sActWord=response.substring(2,response.length()-2);
                Log.v("Test",sActWord);
                setActualWordTextView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Test", "Error al StringRequest");
            }
        });
        queue.add(stringRequest);
    }

    private void saveGame(float punctuation){
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        Game game = new Game(nickName,sActWord,punctuation);
        userController.insertGame(game);
        actUserInfo(nickName, punctuation);
        getStringRequest();
    }

    private void actUserInfo(String nickName, float punctuation) {
        User user = userController.findByNick(nickName);
        user.punctuation +=  punctuation;
        user.numGames++;
        userController.updateUser(new User(user.getNickName(),user.getPunctuation(),user.getNumGames()));
    }

}