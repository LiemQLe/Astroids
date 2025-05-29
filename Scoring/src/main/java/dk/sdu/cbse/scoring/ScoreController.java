package dk.sdu.cbse.scoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/score")
public class ScoreController {
    private int playerScore = 0;
    private int enemyScore = 0;

    @GetMapping("/player")
    public String getPlayerScore() {
        return String.valueOf(playerScore);
    }

    @GetMapping("/enemy")
    public String getEnemyScore() {
        return String.valueOf(enemyScore);
    }

    @PostMapping("/player")
    public void addScore(@RequestParam int points) {
        playerScore += points;
    }

    @PostMapping("/enemy")
    public void addEnemyScore(@RequestParam int points) {
        enemyScore += points;
    }
}
