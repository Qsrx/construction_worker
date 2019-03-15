package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/5 005 20:23:47.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class AnswerCardBean {

    /**
     * questions_id : 1
     * choose_answer : A
     * exercise_type : 1
     */

    private String questions_id;
    private String choose_answer;
    private String exercise_type;

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
    }

    public String getChoose_answer() {
        return choose_answer;
    }

    public void setChoose_answer(String choose_answer) {
        this.choose_answer = choose_answer;
    }

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }
}
