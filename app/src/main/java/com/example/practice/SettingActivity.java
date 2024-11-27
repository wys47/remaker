package com.example.practice;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1000;

    private Dialog uploadPopup;
    private Button[] uploadPopupButtons = new Button[ListOfGames.genreList.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        uploadPopup = new Dialog(this);
        uploadPopup.setContentView(R.layout.dialog_game_upload);
        uploadPopup.setCancelable(true);

        LinearLayout child = uploadPopup.findViewById(R.id.genreTagLayout);
        ListOfGames.addViewFromList(this, child, 280, "genre", 16, 36, uploadPopupButtons);
        int idx = 0;
        for (Button button : uploadPopupButtons) {
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
    public void onUploadMyGame(View view) {
        for (int i = 0; i < genreSelected.length; ++i) genreSelected[i] = false;
        uploadPopup.show();
    }
    public void gameUploadDialogClose(View view) {
        uploadPopup.dismiss();
    }

    private void genreSelectByNum(int n, int id, View view) {
        genreSelected[n] = !genreSelected[n];
        Button button = view.findViewById(id);

        button.setBackgroundTintList(ColorStateList.valueOf(genreSelected[n] ? ContextCompat.getColor(this, R.color.grey0) : ContextCompat.getColor(this, R.color.grey2)));
        button.setTextColor(genreSelected[n] ? Color.WHITE : Color.BLACK);
    }

    public void onHomeClick(View view)
    {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
