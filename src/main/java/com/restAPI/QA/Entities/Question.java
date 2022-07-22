package com.restAPI.QA.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name =  "question")
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String questionBody;
    private String sentBy;

    //For some reason Booleans are passed as Null in Postman, so
    //integer values 1 and 0 are used here
    private int isAnonymous;

    private String answerBody;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id", nullable=false)
    private UserProfile userProfile;

    public Question() {
    }

    public Question(Long id, String questionBody, String sentBy, int isAnonymous) {
        this.id = id;
        this.questionBody = questionBody;
        this.sentBy = sentBy;
        this.isAnonymous = isAnonymous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }


    public int getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(int isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }
}
