package com.restAPI.QA.Services;

import com.restAPI.QA.Entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> getAll();
    Question saveQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllByUsername(String username);

    void deleteQuestionById(Long id);
}
