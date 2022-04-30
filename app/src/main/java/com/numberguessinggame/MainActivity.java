package com.numberguessinggame;

// Import necessary classes and packages
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int MAX_NUMBER = 100;
    public static final Random RANDOM = new Random();
    private TextView message;
    private TextView text;
    private EditText number;
    private Button play;
    private int numberToFind, numberOfTries;
    private Button guessRecordBtn;
    public int numGames = 0;

    // Initiate startup logic for the game app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.txtMessage);
        number = findViewById(R.id.txtNumber);
        play = findViewById(R.id.btnPlay);
        guessRecordBtn = findViewById(R.id.read);
        text = findViewById(R.id.text);

        // Read from guess record file when guess record button is clicked
        guessRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = readFromFile("file.txt");
                text.setText(content);
            }
        });
        play.setOnClickListener(this);
        newGame();
    }

    // When the guess button is clicked and the onClick() event gets fired
    public void onClick(View view) {
        if (view == play) {
            validatePlay();
        }
    }

    // Initiate a new game
    private void newGame() {
        numGames++;
        numberToFind = RANDOM.nextInt(MAX_NUMBER) + 1;
        message.setText("Range 1-100. Unlimited tries!");
        number.setText("");
        numberOfTries = 0;
    }

    // Check if guess is correct or not, then return the appropriate message
    private void validatePlay() {
        int num = Integer.parseInt(number.getText().toString()); // to convert input to integer
        numberOfTries++;

        if (num == numberToFind) {
            Toast.makeText(this, "Congratulations! You got it in " + numberOfTries + " tries.", Toast.LENGTH_SHORT).show();
            String content = Integer.toString(numberOfTries);
            guessRecord(content);
            newGame();
        } else if (num > numberToFind) {
            Toast.makeText(this, "Too high!", Toast.LENGTH_SHORT).show();
        } else if (num < numberToFind) {
            Toast.makeText(this, "Too low!", Toast.LENGTH_SHORT).show();
        }
    }

    // When user exits app, delete guess record file
    @Override
    protected void onPause() {
        deleteFile("file.txt");
        super.onPause();
    }

    // When app is shut down, delete guess record file
    @Override
    protected void onDestroy(){
        deleteFile("file.txt");
        super.onDestroy();
    }

    // Write to guess record file
    private void guessRecord(String contents) {
        /* Original solution:
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "file.txt"));
            writer.write(contents.getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        File path = getApplicationContext().getFilesDir();
        File file  = new File(path, "file.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(contents);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read from guess record file
    public String readFromFile(String fileName) {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
