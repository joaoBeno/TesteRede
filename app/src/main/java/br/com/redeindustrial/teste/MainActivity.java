package br.com.redeindustrial.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ponteiros;
    private float posAtual = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ponteiros = (ImageView) findViewById(R.id.ponteiro);
    }

    public void girarHorario(View v) {
        girar(1);
    }

    public void girarAntiHorario(View v) {
        girar(-1);
    }

    private void girar(int passo) {
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(posAtual, posAtual + (30.0f * passo),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        posAtual = posAtual + (30.0f * passo);

        animRotate.setDuration(250);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        ponteiros.startAnimation(animSet);
    }
}
