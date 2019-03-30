package cs.ut.ee.ermmemory2;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    private List<Question> questionList;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);

        Button estonianButton = findViewById(R.id.et_bt);
        Button englishButton = findViewById(R.id.en_bt);


        readQuestions();

        estonianButton.setOnClickListener(this);
        englishButton.setOnClickListener(this);
    }

    private void initializePlayer() {
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.chalk);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0,0);
            }
        });
        videoView.start();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart called:");
        initializePlayer();
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop called");
        releasePlayer();
    }

    private void releasePlayer() {
        videoView.stopPlayback();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.et_bt) {
            MLString.setCurrentLanguage(MLString.Language.Estonian);
        }
        else if(view.getId() == R.id.en_bt) {
            MLString.setCurrentLanguage(MLString.Language.English);
        }

        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("question", (Serializable)questionList);
        ResultActivity.userScore = 0;
        startActivity(intent);
    }

    private void readQuestions() {
        questionList = QuestionReader.getQuestions(this);

        for(Question question : questionList) {
            Log.i(TAG, question.toString());
        }
    }
}
