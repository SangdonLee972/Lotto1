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

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private TextView lottoList;
    ArrayList<String> arrList = new ArrayList<String>();
    ArrayList<String> lottoset = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottoList = findViewById(R.id.lotto_list1);

        Button createNum = findViewById(R.id.button1);
        createNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottoList.setText("");

          for (int j = 0; j <= 5; j++) {
                    while (true) {
                        int num = (int) (Math.random() * 45);

                        if (!lottoset.contains(String.valueOf(num)) && num != 0) {
                            lottoset.add(num + "");
                        }
                        else
                        {
                            Log.d("AA","재추첨");
                            num = (int) (Math.random() * 45);
                        }


                        if (lottoset.size() > 7) {
                            break;
                        }


                    }


                    arrList.addAll(lottoset);










                    for (int i = 0; i < arrList.size(); i++) {
                        if (i < 5) {
                            if(Integer.valueOf(arrList.get(i)) < 10) {
                                      lottoList.append("0" + arrList.get(i) + ",   ");
                          }
                          else{
                              lottoList.append(arrList.get(i) + ",   ");
                          }
                        } else if (i == 6) {
                                lottoList.append((arrList.get(i) + "    \n\n"));
                        }
                    }

                    lottoset.clear();
                    arrList.clear();




                }
            }
        });

    }
}