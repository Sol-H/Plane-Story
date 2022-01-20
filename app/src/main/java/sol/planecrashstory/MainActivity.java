package sol.planecrashstory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    NodeMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream prc = getCSVRes();
        map = new NodeMap(prc);
        Button reset = (Button) findViewById(R.id.reset);
        reset.setVisibility(View.GONE);
        setTexts();
    }

    protected InputStream getCSVRes(){
        Resources res = getResources();
        return res.openRawResource(R.raw.data);
    }

    protected void setTexts(){

        TextView tvDesc = (TextView) findViewById(R.id.description);
        TextView tvQues = (TextView) findViewById(R.id.question);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        tvDesc.setText(map.currentNode().getDescription());
        tvQues.setText(map.currentNode().getQuestion());

        if (map.endOfGame()){
            endOfGame();
        }
        else {
            button1.setVisibility(View.VISIBLE);
            if (map.currentNode().getQuestion().equals("-")) {
                button1.setText(getResources().getString(R.string.option_continue));
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
            } else {
                button1.setText(getResources().getString(R.string.option_one));
                button2.setVisibility(View.VISIBLE);

                button1.setText(map.currentNode().getNodeOne().getDescription());
                button2.setText(map.currentNode().getNodeTwo().getDescription());

                if (map.twoChoices()) {
                    button3.setVisibility(View.GONE);
                } else {
                    button3.setVisibility(View.VISIBLE);
                    button3.setText(map.currentNode().getNodeThree().getDescription());
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
        InputStream prc = getCSVRes();
        map = new NodeMap(prc);

        TextView tvDesc = (TextView) findViewById(R.id.description);
        TextView tvQues = (TextView) findViewById(R.id.question);

        tvDesc.setTextColor(Color.BLACK);
        tvQues.setTextColor(Color.BLACK);

        ImageView bg = (ImageView) findViewById(R.id.background);
        bg.setBackgroundResource(R.drawable.playing);

        setTexts();
        Button reset = (Button) findViewById(R.id.reset);
        reset.setVisibility(View.GONE);
    }

    public void endOfGame(){
        TextView tvDesc = (TextView) findViewById(R.id.description);
        TextView tvQues = (TextView) findViewById(R.id.question);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        ImageView bg = (ImageView) findViewById(R.id.background);

        if(map.currentNode().getOption1ID() == -2){
            bg.setBackgroundResource(R.drawable.death);
            tvDesc.setTextColor(Color.WHITE);
            tvQues.setTextColor(Color.WHITE);
        }
        else{
            bg.setBackgroundResource(R.drawable.alive);
        }

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        Button reset = (Button) findViewById(R.id.reset);
        reset.setVisibility(View.VISIBLE);
    }

}