package sol.planecrashstory;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
    Button reset;
    ImageView bg;
    MediaPlayer player;
    Animation buttonAnim;
    Animation textAnim;

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
        reset = (Button) findViewById(R.id.reset);
        bg = (ImageView) findViewById(R.id.background);

        buttonAnim = AnimationUtils.loadAnimation(this, R.anim.slide);
        textAnim = AnimationUtils.loadAnimation(this, R.anim.slide2);

        // Setting Input Stream for CSV File
        InputStream prc = getCSVRes();
        map = new NodeMap(prc);

        reset.setVisibility(View.GONE);
        setTexts();
    }

    protected InputStream getCSVRes(){
        Resources res = getResources();
        return res.openRawResource(R.raw.data);
    }

    protected void setTexts(){

        tvDesc.setText(map.currentNode().getDescription());
        tvQues.setText(map.currentNode().getQuestion());
        slideAnimation(tvDesc);
        slideAnimation(tvQues);

        if (map.endOfGame()){
            endOfGame();
        }
        else {
            button1.setVisibility(View.VISIBLE);
            if (map.currentNode().getQuestion().equals("-")) {
                button1.setText(getResources().getString(R.string.option_continue));
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                slideAnimation(button1);
            } else {
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

    public void resetActor(View view){
        //button for restarting game

        Intent game = new Intent(GameActivity.this,MainActivity.class);
        startActivity(game);

    }

    public void endOfGame(){

        if(map.currentNode().getOption1ID() == -2){
            bg.setBackgroundResource(R.drawable.death);
            tvDesc.setTextColor(Color.WHITE);
            tvQues.setTextColor(Color.WHITE);
            reset.setTextColor(Color.WHITE);
        }
        else{
            bg.setBackgroundResource(R.drawable.alive);
        }

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        reset.setVisibility(View.VISIBLE);

        slideAnimation(reset);
    }

    public void slideAnimation(Button buttonOne, Button buttonTwo, Button buttonThree){
        buttonOne.startAnimation(buttonAnim);
        buttonTwo.startAnimation(buttonAnim);
        buttonThree.startAnimation(buttonAnim);
    }

    public void slideAnimation(Button buttonOne, Button buttonTwo){
        buttonOne.startAnimation(buttonAnim);
        buttonTwo.startAnimation(buttonAnim);
    }

    public void slideAnimation(Button button){
        button.startAnimation(buttonAnim);
    }

    public void slideAnimation(TextView text){
        text.startAnimation(textAnim);
    }

}