package com.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int MAX_NUMBER = 100;
    public static final Random RANDOM = new Random();
    private TextView message;
    private EditText number;
    private Button play;
    private int numberToFind, numberOfTries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.txtMessage);
        number = (EditText) findViewById(R.id.txtNumber);
        play = (Button) findViewById(R.id.btnPlay);

        play.setOnClickListener(this);
        newGame();
    }

    public void onClick(View view) {
        if (view == play) {
            validatePlay();
        }
    } // end of onClick

    private void newGame() {
        numberToFind = RANDOM.nextInt(MAX_NUMBER) + 1;
        message.setText("Range 1-100. Unlimited tries!");
        number.setText("");
        numberOfTries = 0;
    }

    private void validatePlay() {
        int num = Integer.parseInt(number.getText().toString()); // to convert input to integer
        numberOfTries++;

        if (num == numberToFind) {
            Toast.makeText(this, "Congratulations! You got it in " + numberOfTries + " tries.", Toast.LENGTH_SHORT).show();
            newGame();
        } else if (num > numberToFind) {
            Toast.makeText(this, "Too high!", Toast.LENGTH_SHORT).show();
        } else if (num < numberToFind) {
            Toast.makeText(this, "Too low!", Toast.LENGTH_SHORT).show();
        }
    }
}