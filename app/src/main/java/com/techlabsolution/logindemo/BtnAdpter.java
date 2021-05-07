package com.techlabsolution.logindemo;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BtnAdpter extends RecyclerView.Adapter<BtnAdpter.BtnHolder> {

    Context context;
    ClsBtnPosition[] myClsBtns;
    int[] colorInt = new int[]{R.color.test1, R.color.test2, R.color.test3, R.color.test4, R.color.test5, R.color.test6, R.color.test7, R.color.test8, R.color.test9, R.color.test10, R.color.test11, R.color.test12, R.color.test13, R.color.test14, R.color.test15};
    int counter = 0;

    public BtnAdpter(ClsBtnPosition[] myClsBtns, Context context) {
        this.myClsBtns = myClsBtns;
        this.context = context;
    }

    @NonNull
    @Override
    public BtnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_btn, parent, false);
        BtnHolder btnHolder = new BtnHolder(listItem);
        return btnHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BtnHolder holder, int position) {
        holder.btn.setText(myClsBtns[position].btnNo);
        holder.btn.setBackgroundColor(context.getResources().getColor(colorInt[myClsBtns[position].colorPosition]));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClsBtns[position].colorPosition = counter;
                holder.btn.setBackgroundColor(context.getResources().getColor(colorInt[myClsBtns[position].colorPosition]));

                if (counter == 14) {
                    counter = 0;
                } else {
                    counter++;
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.itemView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myClsBtns.length;
    }

    public class BtnHolder extends RecyclerView.ViewHolder {
        Button btn;

        public BtnHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
