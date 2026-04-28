package com.shubham.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
@Service


public class UserAuthService {
    private final UserRepo userRepo;
    @Autowired
    private final PasswordEncoder ps;
    private final FeedbackRepo feedbackRepo;
    private final AuthenticationManager authenticationManager;

    public UserAuthService(UserRepo userRepo, PasswordEncoder ps, FeedbackRepo feedbackRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.ps = ps;
        this.feedbackRepo = feedbackRepo;
        this.authenticationManager = authenticationManager;
    }

    public Users registerUser(Users user){
        user.setPassword(ps.encode(user.getPassword()));
        user.setRole(List.of("USER"));
        return userRepo.save(user);
    }

    public String loginUser(Users users){


        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
               return "login";
    }

    public Feedback feedback(Feedback feedback,String username){
        Users users=userRepo.findByUsername(username);
        System.out.println(username);
        System.out.println(users);
        feedback.setUsers(users);
        return feedbackRepo.save(feedback);
    }
    public List<Feedback> feedbackList(){
        return feedbackRepo.findAll();
    }
    public Feedback byid(Integer id){
        return feedbackRepo.findById(id).orElseThrow();
    }
    public Feedback Updatebyid(Integer id,Feedback fd){
        Feedback feed=feedbackRepo.findById(id).orElseThrow();
        if (fd.getUsers()!= null) {
            feed.setUsers(fd.getUsers());

        }
        if (fd.getFeedback() != null) {
            feed.setFeedback(fd.getFeedback());

        }
        return feedbackRepo.save(feed);
    }
    public String Deletebyid(Integer id){
        feedbackRepo.deleteById(id);

        return "delete";
    }

    public List<Feedback> myfeedback(String username){
        return feedbackRepo.findByUsersUsername(username);
    }

    public Feedback editmyfeedback(String username,Integer id,String edidfd){
        Feedback f=feedbackRepo.findById(id).orElseThrow();
        if (!f.getUsers().getUsername().equals(username)){
            throw  new  RuntimeException("access denied");
        }
        f.setFeedback(edidfd);
        return feedbackRepo.save(f);

    }


}
