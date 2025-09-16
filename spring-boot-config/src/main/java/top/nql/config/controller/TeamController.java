package top.nql.config.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nql.config.model.Team;

@RestController
public class TeamController {
    @PostMapping("/team")
   public ResponseEntity<Team> addTeam(@Valid @RequestBody Team team){
        return ResponseEntity.ok(team);
    }
}
