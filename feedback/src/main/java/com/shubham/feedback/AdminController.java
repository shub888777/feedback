package com.shubham.feedback;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@Data
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserAuthService us;
    @PostMapping("/hi")
    public Users register(@RequestBody Users users){
        return us.registerUser(users);
    }

    @GetMapping("/")
    public List<Feedback> feedbackList(Principal p){
        System.out.println(p.getName());

        return us.feedbackList();
    }
    @DeleteMapping("/{id}")
    public String deletefeedback(@PathVariable Integer id){
        return us.Deletebyid(id);
    }
    @GetMapping("/{id}")
    public Feedback feedbackbyid(@PathVariable Integer id){

        return us.byid(id);
    }

    @PatchMapping("/{id}")
    public Feedback pATCHfeedback(@PathVariable Integer id,@RequestBody Feedback Fd){
        return us.Updatebyid(id,Fd);
    }

}
