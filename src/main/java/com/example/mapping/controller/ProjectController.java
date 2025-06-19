package com.example.mapping.controller;

import com.example.mapping.entity.Project;
import com.example.mapping.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public Project saveProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id,@RequestBody Project project){
        return projectService.updateProject(id,project);
    }

    @GetMapping
    public ResponseEntity<?> getAllProject(@RequestParam (defaultValue = "1")int pageNo, @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(projectService.getAllProjectPage(pageNo,pageSize));
//        return projectService.getAllProjectPage(pageNo,pageSize);
    }
}
