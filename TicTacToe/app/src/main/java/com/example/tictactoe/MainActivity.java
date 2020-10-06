package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    boolean player1 = true;
    byte[] game = {0,0,0,0,0,0,0,0,0};
    String winner = "";
    boolean gameActive = false;
    View button;

    int[][] winPosition = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public boolean checkWin(int player){
        for(int[] item : winPosition){
            int counter = 0;
            for(int place : item){
                if(game[place] == player){
                    counter++;
                }
            }
            if(counter == 3){
                return true;
            }
        }
        return false;
    }


    public void gameMove(View view){
        ImageView viewId = (ImageView) view;
        int gameField = Integer.parseInt(viewId.getTag().toString());

        if(!gameActive || game[gameField] != 0){
            return;
        }

        viewId.setTranslationY(-1500);

        if(player1 == true){
            viewId.setImageResource(R.drawable.yellow_coin);
            game[gameField] = 1;
            if(checkWin(1)){
                winner = "Player 1";
                gameActive = false;
            }
            player1 = false;
        }else{
            viewId.setImageResource(R.drawable.red_coin);
            game[gameField] = 2;
            if(checkWin(2)){
                winner = "Player 2";
                gameActive = false;
            }
            player1 = true;
        }
        viewId.animate().translationYBy(1500).rotation(1800).setDuration(1000);

        if(winner != ""){
            Toast.makeText(this,winner+" wins the game",Toast.LENGTH_SHORT).show();
            button.setVisibility(View.VISIBLE);
        }else{
            int countGame = 0;
            for(int item : game){
                if(item == 0){
                    countGame++;
                }
            }
            if(countGame == 0){
                Toast.makeText(this," No winner!",Toast.LENGTH_SHORT).show();
                gameActive = false;
                button.setVisibility(View.VISIBLE);
            }
        }

    }

    public void clearGame(){
        GridLayout grid = findViewById((R.id.grid1));
        for(int i = 0; i<grid.getChildCount(); i++){
            ImageView img = (ImageView) grid.getChildAt(i);
            img.setImageDrawable(null);
        }
    }

    public void buttonClick(View view){
        button = view.findViewById(R.id.button);
        gameActive = true;
        winner = "";
        game = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        clearGame();
        button.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}