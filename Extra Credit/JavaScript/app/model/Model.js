class _Model {
    constructor() {
        this.game = null;
    }

    initGame(difficulty, player) {
        this.game = new Game(difficulty, player);
    }
}

const Model = Object.freeze({
    instance: new _Model()
});