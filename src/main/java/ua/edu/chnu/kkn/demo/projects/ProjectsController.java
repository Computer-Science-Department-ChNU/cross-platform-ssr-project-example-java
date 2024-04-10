package ua.edu.chnu.kkn.demo.projects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @GetMapping
    public String projects(Model model) {
        return "projects/list";
    }
}
