package cat.tecnocampus.mobileapps.practica2.MontasellVallsGerard.NogueraRecasensAntoniJordi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rv_RankAdapter extends RecyclerView.Adapter<rv_RankAdapter.ViewHolder>{
    ArrayList<User> data;
    private ItemClickListener clickListener;
    private int rowLayout;
    public rv_RankAdapter(ArrayList<User> data) {
        this.data = data;
    }

    public rv_RankAdapter(ArrayList<User> data, int rowLayout) {
        this.data = data;
        this.rowLayout = rowLayout;
    }


    @NonNull
    @Override
    public rv_RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull rv_RankAdapter.ViewHolder holder, int position) {
        User currentUser=data.get(position);
        holder.tv_User.setText(String.valueOf(currentUser.getNickName()));
        holder.tv_Punctuation.setText(String.valueOf(currentUser.getPunctuation()));
        holder.tv_NumPlays.setText(String.valueOf(currentUser.getNumGames()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView tv_User;
        public TextView tv_Punctuation;
        public TextView tv_NumPlays;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_User = itemView.findViewById(R.id.tv_User);
            tv_Punctuation = itemView.findViewById(R.id.tv_Puntuation);
            tv_NumPlays = itemView.findViewById(R.id.tv_NumPlays);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }
        public interface ItemClickListener {
            void onClick(View view, int position);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
}
