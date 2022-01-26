package sol.planecrashstory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class VictoryActivity extends AppCompatActivity {

    TextView tapAnywhere;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        tapAnywhere = findViewById(R.id.play_again);

        // Start playing victory music
        player = MediaPlayer.create(VictoryActivity.this,R.raw.victory);
        player.start();
        player.setVolume(75,75);
        player.setLooping(true);

        animateTitle();
        animateTapAnywhere();
    }

    public void resetActor(View view){
        //button for going to restarting game

        Intent main = new Intent(VictoryActivity.this,MainActivity.class);
        startActivity(main);
    }


    public void animateTitle(){
        ImageView title = (ImageView) findViewById(R.id.victory_title);
        // Create zoom in animation using xml file
        Animation animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        // Start animating the image to zoom in
        title.startAnimation(animZoomIn);
    }

    public void animateTapAnywhere(){
        // Create zoom repeat animation
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.zoomrepeat);
        // Start animating the image to zoom in and out
        tapAnywhere.startAnimation(animShake);
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