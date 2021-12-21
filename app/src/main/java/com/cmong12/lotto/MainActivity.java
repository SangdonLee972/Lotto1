package com.cmong12.lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private static final long MAXVALUE = 100000000L;
    private static final long INTERVAL = 100L;

    private TextView lottoList;

    ArrayList<Integer> arrList = new ArrayList();
    // 이 리스트는 string 일 필요가 없음. int로 변경
    // append에 사용시 string으로 변경하면 됨. 아래 코드에 설명

    ArrayList<Integer> lottoset = new ArrayList<>();
    // 같은 이유로 이 리스트로 string일 필요가 없음.

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottoList = findViewById(R.id.lotto_list1);

        Button createNum = findViewById(R.id.button1);
        createNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 재사용성을 높이기 위해 불필요한 반복문을 제거하고 추출 함수와 출력함수로 분리
                extract();
                print();
            }
        });
    }

    private void print() {
        // 카운트 다운 타이머의 첫번째 인자는 최대 시간(mills)
        // 두번째 인자는 반복할 시간 간격(mills
        timer = new CountDownTimer(MAXVALUE, INTERVAL) {
            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                if (i >= arrList.size()) {
                    // 최대 범위까지 도달하면 종료
                    cancel();
                } else {
                    String result = String.format("%02d, ", arrList.get(i));
                    Log.e("result[" + i + "]", result);
                    lottoList.append(result);
                    // 표현식 활용
                    // %d 는 십진수, %f 는 실수
                    // %02d 는 2자리를 채워서 빈자리를 0으로 채움
                    // $.1f 는 소숫점 이하 1자리로 반올림해서 표현
                }

                if (i % 6 == 5) {
                    // 6번마다 줄바꿈
                    lottoList.append("\n\n");
                }
                // 한번 반복마다 +1
                i++;
            }

            @Override
            public void onFinish() {

            }
        }.start(); // start를 해주지 않으면 시작하지 않음.
    }

    private void extract() {
        lottoList.setText("");
        arrList.clear();

        for (int j = 0; j <= 5; j++) {
            lottoset.clear();
            while (lottoset.size() < 7) {
                int num = (int) (Math.random() * 45);

                if (!lottoset.contains(num) && num != 0) {
                    lottoset.add(num);
                } else {
                    Log.d("AA", "재추첨");
                    continue;
                    // 컨티뉴는 아래 모든 동작을 중지하고 다시 반복하는 실행문
                    // 브레이크가 아래 모든 동작을 중지하고 반복문을 빠져나간다면
                    // 컨티뉴는 반복문 최상단으로 올라가는 차이가 있음
                }

                Log.e("lottoset[" + j + "]", lottoset.size() + "");
            }
            Log.e("arrList", arrList.size() + "");
            arrList.addAll(lottoset);
        }


    }
}