package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    ArrayList<User> data;

    public RecycleViewAdapter(ArrayList<User> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rank,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        User currentUser=data.get(position);
        holder.tv_User.setText(String.valueOf(currentUser.getNickName()));
        holder.tv_Punctuation.setText(currentUser.getPunctuation());
        holder.tv_NumPlays.setText(currentUser.getNumGames());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_User;
        public TextView tv_Punctuation;
        public TextView tv_NumPlays;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_User = itemView.findViewById(R.id.tv_User);
            tv_Punctuation = itemView.findViewById(R.id.tv_Puntuation);
            tv_NumPlays = itemView.findViewById(R.id.tv_NumPlays);
        }
    }
}
