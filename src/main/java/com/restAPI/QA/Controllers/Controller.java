package com.restAPI.QA.Controllers;

import com.restAPI.QA.Entities.Question;
import com.restAPI.QA.Entities.UserProfile;
import com.restAPI.QA.Repositories.UserRepository;
import com.restAPI.QA.Services.QuestionService;
import com.restAPI.QA.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class Controller {


    @Autowired
    QuestionService questionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    //Post question
    @PostMapping("/{username}")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question, @PathVariable(name = "username") String username) {
        UserProfile userToAsk = userRepository.findByUsername(username);


        //Check if username exists, else throw not found status
        if(userToAsk != null){
            question.setUserProfile(userRepository.findByUsername(username));
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        //If question !anonymous i.e 0 , display sender username. Else, sender name appears as "anonymous"
        if(question.getIsAnonymous() == 0) {
            question.setSentBy(userService.getCurrentUser().getUsername());
        }else{
            question.setSentBy("Anonymous");
        }

        return ResponseEntity.ok(this.questionService.saveQuestion(question));
    }

    //Post answer to question
    @PostMapping("/{username}/{questionId}")
    public ResponseEntity<String> addAnswer(@RequestBody String answer, @PathVariable(name = "username") String username, @PathVariable(name = "questionId") Long id) {
        Question question = questionService.getQuestionById(id);

        //Check if question exists
        if(question==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        question.setAnswerBody(answer);
        questionService.saveQuestion(question);

        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/{username}/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable(name = "username") String username, @PathVariable(name = "questionId") Long id) {
        Question questionToDelete = questionService.getQuestionById(id);

        //Ensure that the user cannot delete other user's questions
        if(questionToDelete.getUserProfile().getUsername().equals(userService.getCurrentUser().getUsername()) ){
            questionService.deleteQuestionById(id);
            return ResponseEntity.ok("Question Deleted!");

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


        @GetMapping("/{username}")
    public ResponseEntity<List<Question>> getQuestionList (@PathVariable(name = "username") String username){
        //Fetch all questions in DB linked to given username
        return ResponseEntity.ok(this.questionService.getAllByUsername(username));
    }


}
