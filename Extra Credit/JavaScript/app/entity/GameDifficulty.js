class _GameDifficulty {
    constructor(name) {
        this.name = name;
        Object.freeze(this);
    }
}

const GameDifficulty = Object.freeze({
    B: new _GameDifficulty("Beginner"),
    E: new _GameDifficulty("Easy"),
    N: new _GameDifficulty("Normal"),
    H: new _GameDifficulty("Hard"),
    I: new _GameDifficulty("Impossible"),
});