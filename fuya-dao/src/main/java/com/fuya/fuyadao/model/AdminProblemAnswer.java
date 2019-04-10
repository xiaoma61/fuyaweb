package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.entity.PROBLEM;

public class AdminProblemAnswer {
    private PROBLEM problem;
    private CHOOSE choose;
    public  AdminProblemAnswer(PROBLEM problem, CHOOSE choose){
        this.problem=problem;
        this.choose=choose;

    }

    public PROBLEM getProblem() {
        return problem;
    }

    public void setProblem(PROBLEM problem) {
        this.problem = problem;
    }

    public CHOOSE getChoose() {
        return choose;
    }

    public void setChoose(CHOOSE choose) {
        this.choose = choose;
    }
}
