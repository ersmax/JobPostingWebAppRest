package com.example.JobAppRest;

import com.example.JobAppRest.model.JobPost;
import com.example.JobAppRest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping(path = "/jobPosts", produces = {"application/json"})
    public List<JobPost> viewJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int id) {
        return jobService.getJob(id);
    }

    @PostMapping(path = "/jobPost", consumes = {"application/json"})
    public JobPost addJob(@RequestBody JobPost jobPost) {
        jobService.addJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @PutMapping("/jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @DeleteMapping("/jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int id) {
        jobService.deleteJob(id);
        return "Deleted";
    }

    @GetMapping("/load")
    public String loadData() {
        jobService.load();
        return "Success";
    }
}
