class Button {
    constructor(constraint, text, theme) {
        this.constraint = constraint;
        this.text = text;
        this.theme = theme;
        this.state = ButtonState.DEPRESSED;
    }

    get x() {
        return this.constraint.x;
    }

    get y() {
        return this.constraint.y;
    }

    get width() {
        return this.constraint.width;
    }

    get height() {
        return this.constraint.height;
    }

    draw() {
        this.applyTheme();
        rect(this.x, this.y, this.width, this.height,
            this.theme[this.state].round)
        this.applyTextTheme();
        text(" " + this.text, this.x, this.y, this.width, this.height);
    }

    applyTheme() {
        let theme = this.theme[this.state]
        stroke(theme.stroke);
        strokeWeight(theme.strokeWeight);
        fill(theme.fill);
    }

    applyTextTheme() {
        let theme = this.theme[this.state].text;
        textAlign(CENTER, CENTER);
        textFont(theme.font);
        textStyle(theme.style);
        textSize(theme.size);
        stroke(theme.stroke);
        strokeWeight(theme.strokeWeight);
        fill(theme.fill);
    }

    isClicked(x, y) {
        return this.x + this.width >= x && this.x <= x && this.y <= y
            && this.y + this.height >= y;
    }

    handleClick(x, y) {
        if (this.isClicked(x, y)) {
            if (this.state === ButtonState.DEPRESSED) {
                this.state = ButtonState.PRESSED;
            } else {
                this.state = ButtonState.DEPRESSED;
            }
            return true;
        }
        return false;
    }

    handleResize() {
        this.constraint.update();
    }
}