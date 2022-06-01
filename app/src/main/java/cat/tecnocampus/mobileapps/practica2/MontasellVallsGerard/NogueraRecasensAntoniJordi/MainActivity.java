package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserController userController;
    String nickName;
    EditText et_UserNick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userController = new UserController(getApplication());
        et_UserNick = findViewById(R.id.et_UserNick);
    }
    /* TODO: Create Click Function To Log In */
    public void logInOnClick(View view) {
        nickName = et_UserNick.getText().toString();
        if(checkIfExist(nickName)) {
            Intent intent = new Intent(this, PlayGame.class);
            intent.putExtra("nickName", nickName);
            startActivity(intent);
        }
        else{
            //Show user dosent exist
            Toast.makeText(this, "User dosen't exist", Toast.LENGTH_LONG).show();
        }
    }

    /* TODO: Create Click Function To Sing Up */
    public void singInOnClick(View view) {
        nickName = et_UserNick.getText().toString();
        Log.e("TEST", nickName);
        //TODO: if dosent exist Insert in case that exist in bbdd cancel and say that all ready exits
        if(!checkIfExist(nickName)) {
            User user = new User(nickName, 0, 0);
            Log.e("TEST", String.valueOf(user == null));
            userController.insertUser(user);
            Intent intent = new Intent(this, PlayGame.class);
            intent.putExtra("nickName", nickName);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "User already exist", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkIfExist(String actNickName){
        List<String> allNicks= userController.getAllNicks();
        for(String s : allNicks){
            Log.i("Prueva ",s);
            if(s.compareTo(actNickName)==0) return true;
        }
        return false;
    }

}