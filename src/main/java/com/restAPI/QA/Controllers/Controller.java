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

    @GetMapping("/{username}")
    public ResponseEntity<List<Question>> getQuestionList (@PathVariable(name = "username") String username){
        //Fetch all questions in DB linked to given username
        return ResponseEntity.ok(this.questionService.getAllByUsername(username));
    }


}
