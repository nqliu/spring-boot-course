package top.config.config.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.config.config.model.Team;

@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team) {
        log.info("创建团队：{}", team);
        return team;
    }
}
