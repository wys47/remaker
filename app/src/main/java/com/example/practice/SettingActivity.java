package com.example.practice;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1000;
    private static final int PERMISSION_REQUEST_CODE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    final int genreCnt = 9;
    boolean[] genreSelected = new boolean[genreCnt + 1];
    TextView fileURIText;
    public void onUploadMyGame(View view) {
        for (int i = 1; i <= genreCnt; ++i) genreSelected[i] = false;

        View popUpView = getLayoutInflater().inflate(R.layout.dialog_game_upload, null);
        PopupWindow popupWindow = new PopupWindow(
                popUpView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        View parentLayout = findViewById(android.R.id.content);
        popupWindow.showAtLocation(parentLayout, Gravity.CENTER, 0, 0);

        fileURIText = popUpView.findViewById(R.id.fileURIText);

        Button closePopupButton = popUpView.findViewById(R.id.gameUploadDialogClose);
        closePopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
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

    public void onSelectFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.android.package-archive");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "파일을 선택하세요"), FILE_SELECT_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                fileURIText.setText(uri.toString());
            }
            else {
                Toast.makeText(this, "파일을 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onGoBack(View view)
    {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
