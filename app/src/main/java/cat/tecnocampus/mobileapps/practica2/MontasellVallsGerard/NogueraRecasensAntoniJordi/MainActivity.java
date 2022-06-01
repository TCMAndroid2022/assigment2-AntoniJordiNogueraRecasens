package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> myActivityResultLauncher;
    UserController userController;
    String nickName;
    EditText et_UserNick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_UserNick = findViewById(R.id.et_UserNick);

        myActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){

                }
            }
        });
    }
    /* TODO: Create Click Function To Log In */
    public void logInOnClick(View view) {
        nickName = et_UserNick.getText().toString();
        Intent intent =new Intent(this,PlayGame.class);
        intent.putExtra("nickName",nickName);
        //Todo: Check if exist
        myActivityResultLauncher.launch(intent);
    }

    /* TODO: Create Click Function To Sing Up */
    public void singInOnClick(View view) {
        nickName = et_UserNick.getText().toString();
        //TODO: if dosent exist Insert in case that exist in bbdd cancel and say that all ready exits
        userController.insertUser(new User(nickName,0,0));
        Intent intent =new Intent(this,PlayGame.class);
        intent.putExtra("nickName",nickName);
        myActivityResultLauncher.launch(intent);
    }

}