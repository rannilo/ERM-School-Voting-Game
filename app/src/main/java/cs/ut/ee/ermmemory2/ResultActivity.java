package cs.ut.ee.ermmemory2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by teras on 24.03.18.
 */

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    public static Integer userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        ImageButton homeButton = findViewById(R.id.reset);

        TextView resultText = findViewById(R.id.textView2);
        switch (MLString.getCurrentLanguage()){
            case Estonian:
                resultText.setText("Sinu tulemus on");
                break;
            case English:
                resultText.setText("Your score is");
                break;
        }

        TextView score = findViewById(R.id.textView3);
        score.setText(userScore.toString() + "/7");
        homeButton.setOnClickListener(this);

        TextView description = findViewById(R.id.endResultDescription);
        if (userScore < 4 && userScore > 0){
            switch (MLString.getCurrentLanguage()){
                case Estonian:
                    description.setText("Vastasid mõned küsimused valesti, aga pole hullu! Harjuta veel veidi.");
                    break;
                case English:
                    description.setText("You answered some questions wrong, but no worries! Practice some more.");
                    break;
            }
        }
        else if (userScore >= 4 && userScore < 7){
            switch (MLString.getCurrentLanguage()){
                case Estonian:
                    description.setText("Vastasid rohkem kui pooled küsimused õigesti! Muljetavaldav! Proovi nüüd ka kõik küsimused õigesti saada");
                    break;
                case English:
                    description.setText("You answered more than half of the questions correct! That is impressive! Now try to get all of the questions right!");
                    break;
            }
        }
        else if(userScore == 7){
            switch (MLString.getCurrentLanguage()){
                case Estonian:
                    description.setText("Vastasid kõik küsimused õigesti! Oled tõeline koolivormi ekspert!");
                    break;
                case English:
                    description.setText("You answered all the questions right! You are a true school uniform expert!");
                    break;
            }
        }
        else if (userScore == 0){
            switch (MLString.getCurrentLanguage()){
                case Estonian:
                    description.setText("Sa ei vastanud ühtegi küsimust õigesti. Kuidas sul see küll õnnestus? Proovi pigem uuesti.");
                    break;
                case English:
                    description.setText("You didn't answer any of the questions right. How did that happen? You should try again.");
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.reset) {
            finish();
        }
    }
}
