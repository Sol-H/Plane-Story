package sol.planecrashstory;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class GameActivity extends AppCompatActivity {

    NodeMap map;

    // Text, Buttons, images and sounds.
    TextView tvDesc;
    TextView tvQues;

    Button button1;
    Button button2;
    Button button3;
    Button end_button;

    ImageView bg;
    ImageView textPanel;
    ImageView buttonPanel;

    Animation buttonAnim;
    Animation textAnim;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Defining Buttons, TextViews, ImageViews and animations
        tvDesc = (TextView) findViewById(R.id.description);
        tvQues = (TextView) findViewById(R.id.question);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        end_button = (Button) findViewById(R.id.end);
        bg = (ImageView) findViewById(R.id.background);
        textPanel = (ImageView) findViewById(R.id.textpanel);
        buttonPanel = (ImageView) findViewById(R.id.buttonpanel);

        buttonAnim = AnimationUtils.loadAnimation(this, R.anim.slide);
        textAnim = AnimationUtils.loadAnimation(this, R.anim.slide2);

        // Setting Input Stream for CSV File
        InputStream prc = getCSVRes();
        map = new NodeMap(prc);

        // Start playing game music
        player = MediaPlayer.create(GameActivity.this,R.raw.thinking);
        player.start();
        player.setVolume(75,75);
        player.setLooping(true);

        end_button.setVisibility(View.GONE);
        setTexts();
    }

    protected InputStream getCSVRes(){
        Resources res = getResources();
        return res.openRawResource(R.raw.data);
    }

    protected void setTexts(){

        // Separate the choices in the question textView
        String question = map.currentNode().getQuestion();
        if (question.contains("1:")){
            question = question.replace("1:","\n1:");
        }
        if (question.contains("2:")){
            question = question.replace("2:","\n2:");
        }
        if (question.contains("3:")){
            question = question.replace("3:","\n3:");
        }

        // Set the text for the description and the question
        tvDesc.setText(map.currentNode().getDescription());
        tvQues.setText(question);
        slideAnimation(tvDesc);
        slideAnimation(tvQues);

        if (map.endOfGame()){
            endOfGame();
        }
        else {
            button1.setVisibility(View.VISIBLE);
            if (map.currentNode().getQuestion().equals("-")) {
                // If no questions
                button1.setText(getResources().getString(R.string.option_continue));
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                slideAnimation(button1);
            } else {
                // If there are questions
                button1.setText(getResources().getString(R.string.option_one));
                button2.setVisibility(View.VISIBLE);

                button1.setText(map.currentNode().getNodeOne().getDescription());
                button2.setText(map.currentNode().getNodeTwo().getDescription());

                if (map.twoChoices()) {
                    // Two Options
                    button3.setVisibility(View.GONE);
                    slideAnimation(button1, button2);
                } else {
                    // Three Options
                    button3.setVisibility(View.VISIBLE);
                    button3.setText(map.currentNode().getNodeThree().getDescription());
                    slideAnimation(button1, button2, button3);
                }

            }
        }

    }

    public void optionOneActor(View view){
        //button for option one

        map.decision(1);
        setTexts();
    }

    public void optionTwoActor(View view){
        //button for option two

        map.decision(2);
        setTexts();
    }

    public void optionThreeActor(View view){
        //button for option three

        map.decision(3);
        setTexts();
    }

    public void endActor(View view){
        if(map.currentNode().getOption1ID() == -2) {
            // button for going to death screen
            Intent death = new Intent(GameActivity.this,DeathActivity.class);
            startActivity(death);
        }
        else{
            // button for going to victory screen
            Intent victory = new Intent(GameActivity.this,VictoryActivity.class);
            startActivity(victory);
        }

    }


    public void endOfGame(){
        // makes all buttons gone apart from the reset button, so the user can start the game again

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);

        end_button.setVisibility(View.VISIBLE);
        slideAnimation(end_button);

    }

    // Slide animation for buttons and text.

    public void slideAnimation(Button buttonOne, Button buttonTwo, Button buttonThree){
        buttonOne.startAnimation(buttonAnim);
        buttonTwo.startAnimation(buttonAnim);
        buttonThree.startAnimation(buttonAnim);
        buttonPanel.startAnimation(buttonAnim);
    }

    public void slideAnimation(Button buttonOne, Button buttonTwo){
        buttonOne.startAnimation(buttonAnim);
        buttonTwo.startAnimation(buttonAnim);
        buttonPanel.startAnimation(buttonAnim);
    }

    public void slideAnimation(Button button){
        button.startAnimation(buttonAnim);
        buttonPanel.startAnimation(buttonAnim);
    }

    public void slideAnimation(TextView text){
        text.startAnimation(textAnim);
        textPanel.startAnimation(textAnim);
    }

    // Function to stop the music if the app is minimized.
    @Override
    protected void onPause() {
        player.pause();
        super.onPause();
    }
    // Function to play the music again if the app is reopened.
    @Override
    protected void onResume() {
        player.start();
        super.onResume();
    }

}