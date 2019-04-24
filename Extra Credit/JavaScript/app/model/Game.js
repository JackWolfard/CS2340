class Game {
    constructor(difficulty, player) {
        this.difficulty = difficulty;
        this.universe = new Universe();
        this.player = new Player(player);
    }
}