package sol.planecrashstory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    TextView startView;
    ImageView plane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        startView = findViewById(R.id.start);
        plane = findViewById(R.id.plane);

        // Start playing main menu music
        player = MediaPlayer.create(MainActivity.this,R.raw.mainthemetwo);
        player.start();
        player.setVolume(75,75);
        player.setLooping(true);

        animateTitle();
        animateStartText();
        animatePlane();
    }


   public void startGameActor(View view){
        // When start game is tapped, the game activity is started
       Intent game = new Intent(MainActivity.this,GameActivity.class);
       startActivity(game);
   }


    public void animateTitle(){
        ImageView title = (ImageView) findViewById(R.id.title);
        // Create zoom in animation using xml file
        Animation animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        // Start animating the image to zoom in
        title.startAnimation(animZoomIn);
    }

    public void animateStartText(){
        // Create zoom repeat animation
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.zoomrepeat);
        // Start animating the image to zoom in and out
        startView.startAnimation(animShake);
    }

    public void animatePlane(){
        final Animation animCrash = AnimationUtils.loadAnimation(this, R.anim.crash);
        plane.startAnimation(animCrash);


        animCrash.setAnimationListener(new Animation.AnimationListener()
        {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                plane.setVisibility(View.GONE);

            }
        });
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