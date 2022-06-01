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

import java.util.Locale;

public class PlayGame extends AppCompatActivity {
    private TextView guessingLetter;
    private TextView actualWord;
    private String letterToGuess;
    private String sToGuess;
    private String sActWord = "PALABRA";
    ActivityResultLauncher<Intent> myActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        guessingLetter = (EditText) findViewById(R.id.et_Letter);
        actualWord = (TextView) findViewById(R.id.tv_Answer);

        setActualWordTextView();

        myActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){

                }
            }
        });
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
}