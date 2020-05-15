package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */
    TextView Score2;
    int advancedScore;
    Button[] buttons;

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long l) {
                Toast.makeText(Main2Activity.this,"Get ready in " + String.valueOf(l/1000) + " seconds",Toast.LENGTH_SHORT).show();
                Log.v("Whack-A-Mole-2.0", "Ready CountDown!" + String.valueOf(l/1000)) ;
            }

            @Override
            public void onFinish() {
                Toast.makeText(Main2Activity.this,"Go!",Toast.LENGTH_SHORT).show();
                Log.v("Whack-A-Mole-2.0", "Ready CountDown Complete!");
                placeMoleTimer();
            }

        }.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        new CountDownTimer(1000000,1000){
            @Override
            public void onTick(long l) {
                setNewMole();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
    //private static final int[] BUTTON_IDS = {
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
    //};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Score2 = findViewById(R.id.Score2);
        advancedScore = getIntent().getIntExtra("score",0);
        Score2.setText(String.valueOf(advancedScore));
        buttons = new Button[9];
        for(int i=0; i<buttons.length; i++){
            String buttonID = "button" + (i+1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(buttons[finalI].getText() == "*"){
                        advancedScore++;
                        Log.v("Whack-A-Mole-2.0", "Hit, Score added!");
                    }
                    else{
                        advancedScore--;
                        Log.v("Whack-A-Mole-2.0", "Missed, Score deducted!");
                    }
                    Score2.setText(String.valueOf(advancedScore));
                }
            });
        }
        Log.v("Whack-A-Mole-2.0", "Current User Score: " + String.valueOf(advancedScore));
        readyTimer();
        //for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
        //}
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
   // private void doCheck(Button checkButton)
   // {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
   // }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for(Button i : buttons){
            i.setText("0");
        }
        buttons[randomLocation].setText("*");
        Log.v("Whack-A-Mole-2.0","New Mole Location");
    }
}

