package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Dialog searchPopup;
    private Button[] searchPopupButtons = new Button[ListOfGames.genreList.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchPopup = new Dialog(this);
        searchPopup.setContentView(R.layout.dialog_search_by_label);
        searchPopup.setCancelable(true);

        LinearLayout child = searchPopup.findViewById(R.id.genreTagLayout);
        ListOfGames.addViewFromList(this, child, 280, "genre", 16, 36, searchPopupButtons);
        int idx = 0;
        for (Button button : searchPopupButtons) {
            int finalIdx = idx;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    genreSelectByNum(finalIdx, button.getId(), view);
                }
            });
            idx++;
        }
    }

    boolean[] genreSelected = new boolean[ListOfGames.genreList.length];
    public void onSearchByLabel(View view)
    {
        if (!searchPopup.isShowing()) {
            for (int i = 0; i < ListOfGames.genreList.length; ++i) genreSelected[i] = false;
            searchPopup.show();
        }
        else searchPopup.dismiss();
    }

    private void genreSelectByNum(int n, int id, View view) {
        genreSelected[n] = !genreSelected[n];
        Button button = view.findViewById(id);

        button.setBackgroundTintList(ColorStateList.valueOf(genreSelected[n] ? ContextCompat.getColor(this, R.color.grey0) : ContextCompat.getColor(this, R.color.grey2)));
        button.setTextColor(genreSelected[n] ? Color.WHITE : Color.BLACK);
    }

    public void onProfileClick(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}