package cs.ut.ee.ermmemory2;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by teras on 24.03.18.
 */

public class QuestionReader {
    public static final String FILENAME = "questions.json";

    public static String readFile(AppCompatActivity activity) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(activity.getAssets().open(FILENAME), "UTF-8"));

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line);
        }

        return sb.toString();
    }

    public static List<Question> getQuestions(AppCompatActivity activity) {
        List<Question> questions = new ArrayList<>();

        String json;

        try{
            json = readFile(activity);
        }
        catch (IOException e) {
            return questions;
        }

        try {
            JSONArray jsonQuestionArray = new JSONArray(json);
            for(int i = 0; i < jsonQuestionArray.length(); i++) {
                JSONObject jsonQuestion = jsonQuestionArray.getJSONObject(i);
                Question question = new Question();

                MLString questionText = new MLString();
                questionText.setValue(MLString.Language.Estonian, jsonQuestion.getString("question_ee"));
                questionText.setValue(MLString.Language.English, jsonQuestion.getString("question_en"));
                question.setQuestionText(questionText);

                MLString descriptionText = new MLString();
                descriptionText.setValue(MLString.Language.Estonian, jsonQuestion.getString("description_ee"));
                descriptionText.setValue(MLString.Language.English, jsonQuestion.getString("description_en"));
                question.setDescription(descriptionText);

                question.setFileName(jsonQuestion.getString("picture_file"));
                question.setCorrectAnswer(jsonQuestion.getInt("correct_answer"));

                JSONArray jsonQuestionAnswers = jsonQuestion.getJSONArray("answers");

                for(int j = 0; j < jsonQuestionAnswers.length(); j++) {
                    JSONObject jsonQuestionAnswer = jsonQuestionAnswers.getJSONObject(j);

                    MLString questionAnswer = new MLString();
                    questionAnswer.setValue(MLString.Language.Estonian, jsonQuestionAnswer.getString("answer_ee"));
                    questionAnswer.setValue(MLString.Language.English, jsonQuestionAnswer.getString("answer_en"));

                    question.addAnswer(questionAnswer);
                }

                questions.add(question);
                Log.i("ERM", jsonQuestion.toString());
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
