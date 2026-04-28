package com.shubham.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Integer> {

    public List<Feedback> findByUsersUsername(String username);
}
