package sg.edu.np.WhackAMole;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    int randomLocation;
    int score_text;
    TextView Score;
    Button Button1;
    Button Button2;
    Button Button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_text = 0;
        Score = findViewById(R.id.Score);
        Score.setText(String.valueOf(score_text));
        Button1 = findViewById(R.id.button1);
        Button2 = findViewById(R.id.button2);
        Button3 = findViewById(R.id.button3);

        Log.v("OnCreate:", "Finished Pre-Initialisation!");

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.v("OnStart:", "Starting GUI!");

        setNewMole();

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "Button Left Clicked");
                if (randomLocation == 0) {
                    Log.d("Correct", "Hit, score added!");
                    score_text++;

                } else {
                    Log.d("Missed", "Missed, score deducted!");
                    score_text--;
                }
                Score.setText(String.valueOf(score_text));
                setNewMole();
                checkscore(score_text);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "Button Middle Clicked");
                if (randomLocation == 1) {
                    Log.d("Correct", "Hit, score added!");
                    score_text++;
                } else {
                    Log.d("Missed", "Missed, score deducted!");
                    score_text--;
                }
                Score.setText(String.valueOf(score_text));
                setNewMole();
                checkscore(score_text);
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "Button Right Clicked");
                if (randomLocation == 2) {
                    Log.d("Correct", "Hit, score added!");
                    score_text++;
                } else {
                    Log.d("Missed", "Missed, score deducted!");
                    score_text--;
                }
                Score.setText(String.valueOf(score_text));
                setNewMole();
                checkscore(score_text);
            }
        });

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v("OnPause:", "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v("OnStop:", "Stopped Whack-A-Mole!");
        finish();
    }

    //private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
    //}
    private void checkscore(int score_text){
        if(score_text%10 == 0){
            nextLevelQuery();
        }
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        Log.v("Whack-A-Mole-1.0:", "Advance option given to user!");
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Would you like to advance to advanced mode?").setTitle("Warning! Insane Whack-A-Mole Incoming!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.v("Whack-A-Mole-1.0:", "User accepts!");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Log.v("Whack-A-Mole-1.0:", "User decline!");
            }
        });
        builder.create().show();
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("score",score_text);
        startActivity(intent);
    }

    public void setNewMole() {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        Button1.setText("0");
        Button2.setText("O");
        Button3.setText("O");
        if (randomLocation == 0) {
            Button1.setText("*");
        } else if (randomLocation == 1) {
            Button2.setText("*");
        } else if (randomLocation == 2) {
            Button3.setText("*");
        }
    }
}