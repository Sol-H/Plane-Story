package sol.planecrashstory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player= MediaPlayer.create(MainActivity.this,R.raw.maintheme);
        player.start();
        player.setVolume(75,75);
        animateTitle();
        if (isFinishing()){
            player.stop();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }



   public void startGameActor(View view){
       Intent game = new Intent(MainActivity.this,GameActivity.class);
       player.stop();
       startActivity(game);
   }

    @Override
    protected void onPause() {
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (!taskInfo.isEmpty()) {
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                player.stop();
            }
        }
        super.onPause();
    }


    public void animateTitle(){
        Animation animZoomIn = AnimationUtils.loadAnimation(this,
                R.anim.zoom_in);

        // Start animating the image
        ImageView title = (ImageView) findViewById(R.id.title);
        title.startAnimation(animZoomIn);

        //title.setAnimation(null);

    }
}