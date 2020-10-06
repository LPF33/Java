package com.example.braintraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout background;
    Button playButton;
    GridLayout gridLayout;
    TextView header_middle;
    TextView header_right;
    TextView header_left;
    String[] question;
    int correct = 0;
    int wrong = 0;
    CountDownTimer timerId;

    public void playGame(View view){
        header_right.setText("0/0");
        playButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        showQuestion();
        setTimer();
    }

    public void setTimer(){
        timerId = new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int)millisUntilFinished / 1000;
                int minutes = seconds / 60;
                int newSeconds = seconds - (minutes * 60);

                String firstString = Integer.toString(minutes);
                String secondString = Integer.toString(newSeconds);

                if(newSeconds < 10){
                    secondString = "0" + secondString;
                }
                header_left.setText(firstString + ":" + secondString);
            }

            @Override
            public void onFinish() {
                timerId.cancel();
                playButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                background.setBackgroundColor(Color.WHITE);
                header_left.setText("2:00");
                correct = 0;
                wrong = 0;
                header_middle.setText("Brain Training");
            }
        }.start();
    }

    public void showQuestion(){
        int randomQue = (int)Math.floor(Math.random()*2);
        String[][] questions= {getAdditionQuestion(), getMultiplyQuestion()};
        question = questions[randomQue];
        header_middle.setText(question[0]);
        for(int i = 0; i<gridLayout.getChildCount(); i++){
            Button currentButton = (Button) gridLayout.getChildAt(i);
            currentButton.setText(question[i+2]);
        }
    }

    public void checkAnswer(View view){
        String id = view.getTag().toString();
        Log.i("check", id+question[1]);
        if(Integer.parseInt(id) == Integer.parseInt(question[1])){
            correct++;
            background.setBackgroundColor(Color.GREEN);
        }else{
            wrong++;
            background.setBackgroundColor(Color.RED);
        }
        header_right.setText(Integer.toString(correct)+"/"+Integer.toString(correct+wrong));
        showQuestion();
    }

    public String[] getMultiplyQuestion(){
        int num = (int) Math.floor(Math.random()*21)+1;
        int num2 = (int) Math.floor(Math.random()*21)+1;
        String question = Integer.toString(num) + " * " + Integer.toString(num2);
        String[] questionArr = {question, "2", "", Integer.toString(num*num2-4), Integer.toString(num*num2+1), Integer.toString(num*num2+3)};
        int randomIndex = (int) Math.floor(Math.random()*4)+2;
        String help = questionArr[randomIndex];
        questionArr[2] = help;
        questionArr[randomIndex] = Integer.toString(num*num2);
        questionArr[1] = Integer.toString(randomIndex);
        return questionArr;
    }

    public String[] getAdditionQuestion(){
        int num = (int) Math.floor(Math.random()*101)+1;
        int num2 = (int) Math.floor(Math.random()*101)+1;
        String question = Integer.toString(num) + " + " + Integer.toString(num2);
        String[] questionArr = {question, "2", "", Integer.toString(num+num2-3), Integer.toString(num+num2+1), Integer.toString(num+num2+2)};
        int randomIndex = (int) Math.floor(Math.random()*4)+2;
        String help = questionArr[randomIndex];
        questionArr[2] = help;
        questionArr[randomIndex] = Integer.toString(num+num2);
        questionArr[1] = Integer.toString(randomIndex);
        return questionArr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.notification_background);
        playButton = findViewById(R.id.button);
        gridLayout = findViewById(R.id.grid);
        header_middle = findViewById(R.id.header_middle);
        header_right = findViewById(R.id.header_right);
        header_left = findViewById(R.id.header_left);
    }
}