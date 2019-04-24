class Vector {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    random(min, max) {
        return this.add(max.sub(min));
    }

    sub(other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }

    add(other) {
        return new Vector(this.x + other.x, this.y - other.y);
    }

    get mag() {
        return Math.sqrt(
            Math.pow(this.x, 2),
            Math.pow(this.y, 2)
        );
    }

    distance(other) {
        return Math.floor(other.sub(this).mag());
    }
}