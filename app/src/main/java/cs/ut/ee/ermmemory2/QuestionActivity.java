package cs.ut.ee.ermmemory2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by teras on 24.03.18.
 */

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Question> questionList;
    private Integer questionIndex;
    private RadioGroup radioGroup;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        questionList = (List<Question>)getIntent().getSerializableExtra("question");
        questionIndex = getIntent().getIntExtra("questionIndex", 0);
        Question question = questionList.get(questionIndex);
        correctAnswer = question.getCorrectAnswer();

        TextView textView = findViewById(R.id.questionText);
        textView.setText(question.getQuestionText());

        RadioButton answer0 = findViewById(R.id.questionAnswer0);
        RadioButton answer1 = findViewById(R.id.questionAnswer1);
        RadioButton answer2 = findViewById(R.id.questionAnswer2);

        answer0.setText(question.getAnswer(0));
        answer1.setText(question.getAnswer(1));
        answer2.setText(question.getAnswer(2));

        ImageButton nextButton = findViewById(R.id.questionForward);
        ImageButton homeButton = findViewById(R.id.reset);

        radioGroup = findViewById(R.id.questionAnswerGroup);

        nextButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.questionForward) {
            if(radioGroup.getCheckedRadioButtonId() != -1) {
                int selectedValue = 0;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.questionAnswer0:
                        selectedValue = 0;
                        break;
                    case R.id.questionAnswer1:
                        selectedValue = 1;
                        break;
                    case R.id.questionAnswer2:
                        selectedValue = 2;
                        break;
                }
                if(selectedValue==correctAnswer){
                    ResultActivity.userScore++;
                }

                Intent intent = new Intent(QuestionActivity.this, QuestionDescriptionActivity.class);
                intent.putExtra("question", (Serializable) questionList);
                intent.putExtra("questionIndex", questionIndex);
                intent.putExtra("answeredQuestion", selectedValue);
                Log.i("ERM", "" + selectedValue);
                finish();
                startActivity(intent);
            }
        }
        else if(view.getId() == R.id.reset) {
            finish();
        }
    }
}
