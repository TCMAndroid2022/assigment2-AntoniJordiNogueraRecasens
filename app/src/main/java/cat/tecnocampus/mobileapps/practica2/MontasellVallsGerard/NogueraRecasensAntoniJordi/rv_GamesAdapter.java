package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rv_GamesAdapter extends RecyclerView.Adapter<rv_GamesAdapter.ViewHolder>{

    ArrayList<Game> data;

    public rv_GamesAdapter(ArrayList<Game> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public rv_GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game,parent,false);
        rv_GamesAdapter.ViewHolder vh = new rv_GamesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull rv_GamesAdapter.ViewHolder holder, int position) {
        Game currentGame=data.get(position);
        holder.tv_nickName.setText(String.valueOf(currentGame.getPlayerNickName()));
        holder.tv_word.setText(String.valueOf(currentGame.getWord()));
        holder.tv_punctuation.setText(String.valueOf(currentGame.getPunctuation()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_nickName;
        public TextView tv_word;
        public TextView tv_punctuation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nickName = itemView.findViewById(R.id.tv_nickName);
            tv_word = itemView.findViewById(R.id.tv_word);
            tv_punctuation = itemView.findViewById(R.id.tv_punctuation);
        }
    }
}
