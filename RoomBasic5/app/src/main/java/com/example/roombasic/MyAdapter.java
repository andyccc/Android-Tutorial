package com.example.roombasic;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

    List<Word> allWords = new ArrayList<>();
    boolean useCardView;
    WordViewModel wordViewModel;

    public MyAdapter(boolean useCardView, WordViewModel wordViewModel) {
        this.useCardView = useCardView;
        this.wordViewModel = wordViewModel;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }



    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(useCardView ?  R.layout.cell_card_2 : R.layout.cell_nomal_2, parent, false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        Word word = allWords.get(position);
//        holder.textViewNumber.setText(String.valueOf(word.getId()));
        holder.textViewNumber.setText(String.valueOf(position)); // 显示序号
        holder.textViewEnglish.setText(word.getWord());

        // 避免复用导致的问题
        holder.aSwitch.setOnCheckedChangeListener(null);

        if (word.isChineseInvisible()) {
            holder.textViewChinese.setVisibility(View.GONE);
            holder.aSwitch.setChecked(true);
        } else {
            holder.textViewChinese.setVisibility(View.VISIBLE);
            holder.aSwitch.setChecked(false);
            holder.textViewChinese.setText(word.getChineseMeaning());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q="+ holder.textViewEnglish.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.textViewChinese.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                } else {
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                }
                wordViewModel.updateWords(word);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHodler extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;
        Switch aSwitch;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);

            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);
            aSwitch = itemView.findViewById(R.id.switchBtn);
        }
    }
}
