package cs.ut.ee.ermmemory2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by teras on 24.03.18.
 */

public class Question implements Serializable {
    private String fileName;
    private MLString questionText;
    private MLString description;
    private List<MLString> answers;
    private Integer correctAnswer;

    public Question() {
        this.fileName = "default_picture.png";
        this.questionText = new MLString();
        this.description = new MLString();
        this.answers = new ArrayList<>();
        this.correctAnswer = 0;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void addAnswer(MLString answer) {
        answers.add(answer);
    }

    public String getQuestionText() {
        return questionText.getCurrentValue();
    }

    public void setQuestionText(MLString questionText) {
        this.questionText = questionText;
    }

    public String getDescription() {
        return description.getCurrentValue();
    }

    public void setDescription(MLString description) {
        this.description = description;
    }

    public String getAnswer(int index) {
        return answers.get(index).getCurrentValue();
    }
    public Integer getCorrectAnswer(){
        return correctAnswer;
    }

    public String getFileName(){
        return fileName;
    }

    @Override
    public String toString() {
        return "Question{" +
                "fileName='" + fileName + '\'' +
                ", questionText=" + questionText +
                ", description=" + description +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
