package com.example.practice;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class ListOfGames {
    public static String[] genreList = {"슈팅", "리듬", "생존", "공포", "레이싱", "퍼즐", "힐링", "경영", "시뮬레이션", "샌드박스"};//메인이 되는 장르만 적도록 한다.
    public static String[] detailList = {"2D", "3D", "도트", "횡스크롤", "탑뷰", "로그라이크"};

    public static LinearLayout addViewFromList(Context context, LinearLayout parent, int parentWidth, String listType, int textSize, int buttonMargin, Button[] buttons)
    {
        String[] list = null;
        if (listType == "genre") list = genreList;
        else if (listType == "detail") list = detailList;

        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(params);
        TextView textView = new TextView(context);
        int cntLength = 0;

        int idx = 0;
        for (String item : list) {
            textView.setText(item);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

            Paint paint = textView.getPaint();
            int length = (int) paint.measureText(item) + dpToPx(buttonMargin, context);

            Button button = new Button(context);
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(length, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(buttonParams);
            button.setText(item);
            button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            button.setId(View.generateViewId());
            button.setPadding(0, 0, 0, 0);
            button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.grey2));
            button.setTextColor(Color.BLACK);
            buttons[idx++] = button;

            if (cntLength + length > dpToPx(parentWidth, context)) {
                parent.addView(layout);

                layout = new LinearLayout(context);
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setLayoutParams(params);
                cntLength = 0;
            }

            layout.addView(button);
            cntLength += length;
        }
        parent.addView(layout);

        return parent;
    }

    public static int dpToPx(int dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
