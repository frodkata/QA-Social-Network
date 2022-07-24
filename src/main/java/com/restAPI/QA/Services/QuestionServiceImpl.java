package com.restAPI.QA.Services;

import com.restAPI.QA.Entities.Question;
import com.restAPI.QA.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id){
        Optional<Question> optional = questionRepository.findById(id);
        Question question = null;
        if (optional.isPresent()) {
            question = optional.get();
        }

        return question;
    }

    @Override
    public List<Question> getAllByUsername(String username) {
        List<Question> questions = new ArrayList<>();
        for (Question q : questionRepository.findAll()) {
            if (q.getUserProfile().getUsername().equals(username)) {
                questions.add(q);
            }
        }
        return questions;
    }

    @Override
    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }
}
