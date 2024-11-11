package com.example.practice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListOfGames {
    String[] genreList = {"슈팅", "리듬", "생존", "공포", "레이싱", "퍼즐", "힐링", "경영", "시뮬레이션", "샌드박스"};//메인이 되는 장르만 적도록 한다.
    String[] detailList = {"2D", "3D", "도트", "횡스크롤", "탑뷰", "로그라이크"};

    public void makeViewFromList(Context context, View targetView, String listType, int textSize)
    {
        String[] list = null;
        if (listType == "genre") list = genreList;
        else if (listType == "detail") list = detailList;

        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(context);
        for (String item : list) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setLayoutParams(params);
        }

        textView = null; //null로 만들어 가비지 콜렉터의 동작에 도움을 줌
    }
}
