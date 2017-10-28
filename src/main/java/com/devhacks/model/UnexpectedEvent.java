package com.devhacks.model;

/**
 * Created by Ciprian on 10/28/2017.
 */
public class UnexpectedEvent {

    private Integer id;
    private String type;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private Integer answerId;


    public UnexpectedEvent(Integer id, String type, String question, String answer1, String answer2, String answer3, Integer answerId) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answerId = answerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "UnexpectedEvent{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answerId=" + answerId +
                '}';
    }
}
