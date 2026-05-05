package org.example;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class MyController {

    //Dans une "vraie" application, on stockerait ça en base, pas en mémoire
    private HashMap<String, Game> games = new HashMap<>();

    public MyController() {
        this.games.put("0", new Game("0", 0, 0));
    }

    @GetMapping("/wrestle/{gameID}")
    public ResponseEntity<Game> wrestle(@PathVariable String gameID) {
        Game game = Optional.ofNullable(games.get(gameID))
                .orElseThrow(() -> new GameNotFound("Game " + gameID + "not found"));
        return ResponseEntity
                .status(200)
                .body(game);
    }

    @PostMapping("/wrestle")
    public Game wrestle(Game game) {
        this.games.put(game.getGameID(), game);
        return game;
    }

    // /wrestle/magameID?counterNumber=1
    @PutMapping("/wrestle/{gameID}")
    public Game incrementWrestle(HttpServletRequest request, @RequestParam int counterNumber, @PathVariable String gameID) {
        String navigator = request.getHeader("User-Agent");
        Game game = games.get(gameID);
        switch (counterNumber) {
            case 1 -> game.incrementCount1(navigator);
            case 2 -> game.incrementCount2(navigator);
            default -> throw new UserNotFound("Invalid counter number");
        }
        return game;
    }

    @DeleteMapping("/wrestle/{gameID}")
    public Game razWrestle(@PathVariable String gameID) {
        Game game = games.get(gameID);
        game.raz();
        return game;
    }

    @ExceptionHandler(GameNotFound.class)
    public ResponseEntity<Void> handleException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

}
