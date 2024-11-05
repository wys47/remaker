package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    PopupWindow popupWindow = null;
    final int genreCnt = 9;
    boolean[] genreSelected = new boolean[genreCnt + 1];
    public void onSearchByLabel(View view) {
        Toast.makeText(getApplicationContext(), "열기", Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= genreCnt; ++i) genreSelected[i] = false;

        View popUpView = getLayoutInflater().inflate(R.layout.dialog_search_by_label, null);
        popupWindow = new PopupWindow(
                popUpView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        View parentLayout = findViewById(android.R.id.content);
        popupWindow.showAtLocation(parentLayout, Gravity.CENTER, 0, 0);
    }

    public void onGenreSelect(View view) {
        int id = view.getId();

        if (id == R.id.genre1) genreSelectByNum(1, id, view);
        else if (id == R.id.genre2) genreSelectByNum(2, id, view);
        else if (id == R.id.genre3) genreSelectByNum(3, id,view);
        else if (id == R.id.genre4) genreSelectByNum(4, id,view);
        else if (id == R.id.genre5) genreSelectByNum(5, id, view);
        else if (id == R.id.genre6) genreSelectByNum(6, id,view);
        else if (id == R.id.genre7) genreSelectByNum(7, id,view);
        else if (id == R.id.genre8) genreSelectByNum(8, id,view);
        else if (id == R.id.genre9) genreSelectByNum(9, id,view);
    }
    private void genreSelectByNum(int n, int id, View view) {
        genreSelected[n] = !genreSelected[n];
        Button button = view.findViewById(id);

        button.setBackgroundTintList(ColorStateList.valueOf(genreSelected[n] ? Color.BLACK : ContextCompat.getColor(this, R.color.grey1)));
        button.setTextColor(genreSelected[n] ? ContextCompat.getColor(this, R.color.grey1) : Color.BLACK);
    }

    public void onSettingClick(View view) {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}