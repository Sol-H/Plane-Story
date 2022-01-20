package sol.planecrashstory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.*;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateTitle();
    }

   public void startGameActor(View view){
       Intent game = new Intent(MainActivity.this,GameActivity.class);
       startActivity(game);

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