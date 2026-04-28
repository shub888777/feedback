package com.shubham.feedback;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@Data
@RequestMapping("/users")
public class UserController {

    @Autowired

    private UserAuthService us;

    @PostMapping("/feedback")
    public Feedback feedbackpost (@RequestBody Feedback feedback, Principal principal) {
        return us.feedback(feedback,principal.getName());
    }
    @GetMapping("/myfeedback")
    public List<Feedback> myfeedbackList(Principal principal){
        return us.myfeedback(principal.getName());

    }
    @PatchMapping("/editmyfeedback/{id}/{newfd}")
    public Feedback myfeedbackList(Principal principal,@PathVariable Integer id,@PathVariable String newfd){
        return us.editmyfeedback(principal.getName(),id,newfd);

    }
}