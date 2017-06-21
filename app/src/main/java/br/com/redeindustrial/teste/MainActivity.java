package br.com.redeindustrial.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView ponteiro_h, ponteiro_m, ponteiro_s;
    private float posAtualH = 0.0f, posAtualM = 0.0f, posAtualS = 0.0f;
    private int[] currentTime = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ponteiro_h = (ImageView) findViewById(R.id.ponteiro_h);
        ponteiro_m = (ImageView) findViewById(R.id.ponteiro_m);
        ponteiro_s = (ImageView) findViewById(R.id.ponteiro_s);


        Calendar rightNow = Calendar.getInstance();
        currentTime[0] = rightNow.get(Calendar.HOUR_OF_DAY);
        currentTime[1] = rightNow.get(Calendar.MINUTE);
        currentTime[2] = rightNow.get(Calendar.SECOND);

        girar(30.0f * currentTime[0], ponteiro_h);
        girar(6.0f * currentTime[1], ponteiro_m);
        girar(6.0f * currentTime[2], ponteiro_s);

        setTimer();
    }

    private void girar(float grau, ImageView im) {
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        float posAtual = (im == ponteiro_h) ? posAtualH
                : ((im == ponteiro_m) ? posAtualM : posAtualS);

        final RotateAnimation animRotate = new RotateAnimation(posAtual, posAtual + grau,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        if (im == ponteiro_h) {
            posAtualH = posAtual + grau;
        } else if (im == ponteiro_m) {
            posAtualM = posAtual + grau;
        } else {
            posAtualS = posAtual + grau;
        }

        animRotate.setDuration(250);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        im.startAnimation(animSet);
    }

    private void setTimer() {
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Calendar rightNow = Calendar.getInstance();
                        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                        int currentMinute = rightNow.get(Calendar.MINUTE);
                        int currentSecond = rightNow.get(Calendar.SECOND);

                        if (currentTime[0] != currentHour) {
                            girar(30.0f, ponteiro_h);
                            currentTime[0] = currentHour;
                        }

                        if (currentTime[1] != currentMinute) {
                            girar(6.0f,ponteiro_m);
                            currentTime[1] = currentMinute;
                        }

                        if (currentTime[2] != currentSecond) {
                            girar(6.0f,ponteiro_s);
                            currentTime[2] = currentSecond;
                        }
                    }
                });
            }
        }, 1000, 1000);
    }
}
