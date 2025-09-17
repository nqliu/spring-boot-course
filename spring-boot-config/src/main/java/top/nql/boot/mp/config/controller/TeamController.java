package top.nql.boot.mp.config.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.mp.config.model.Team;

@RestController
public class TeamController {
    @PostMapping("/team")
   public ResponseEntity<Team> addTeam(@Valid @RequestBody Team team){
        return ResponseEntity.ok(team);
    }
}
