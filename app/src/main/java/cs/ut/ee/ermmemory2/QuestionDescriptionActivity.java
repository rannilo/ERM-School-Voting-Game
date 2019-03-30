package cs.ut.ee.ermmemory2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

/**
 * Created by teras on 24.03.18.
 */

public class QuestionDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Question> questionList;
    private Integer questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_desc);

        questionList = (List<Question>)getIntent().getSerializableExtra("question");
        questionIndex = getIntent().getIntExtra("questionIndex", 0);
        Question question = questionList.get(questionIndex);

        //setting correct/incorrect message
        int selectedValue = getIntent().getIntExtra("answeredQuestion", 0);
        TextView resultDescription = findViewById(R.id.resultDescription);
        boolean answerCorrect = Integer.valueOf(selectedValue).equals(questionList.get(questionIndex).getCorrectAnswer());
        if(answerCorrect){
            switch (MLString.getCurrentLanguage()){
                case English:
                    resultDescription.setText("Correct!");
                    break;
                case Estonian:
                    resultDescription.setText("Õige!");
                    break;
            }
        }
        else{
            switch (MLString.getCurrentLanguage()){
                case Estonian:
                    resultDescription.setText("Vale!");
                    break;
                case English:
                    resultDescription.setText("Incorrect");
                    break;
            }
        }

        //correct/incorrect picture
        ImageView imageView = findViewById(R.id.descriptionImage);
        if(answerCorrect) Glide.with(this).load(R.drawable.yes).into(imageView);
        else Glide.with(this).load(R.drawable.oops).into(imageView);

        TextView questionDescription = findViewById(R.id.questionDescription);
        questionDescription.setText(question.getDescription());
        questionDescription.setMovementMethod(new ScrollingMovementMethod());

        ImageButton nextButton = findViewById(R.id.questionForward);
        ImageButton homeButton = findViewById(R.id.descResetButton);

        homeButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.questionForward) {
            if(questionIndex + 1 < questionList.size()) {
                Intent intent = new Intent(QuestionDescriptionActivity.this, QuestionActivity.class);
                intent.putExtra("question", (Serializable) questionList);
                intent.putExtra("questionIndex", questionIndex + 1);
                finish();
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(QuestionDescriptionActivity.this, ResultActivity.class);
                finish();
                startActivity(intent);
            }
        }
        else if(view.getId() == R.id.descResetButton) {
            finish();
        }
    }
}
