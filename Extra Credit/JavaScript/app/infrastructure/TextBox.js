class TextBox {
    constructor(constraint, holderText, theme, fillBackground=false) {
        this.constraint = constraint;
        this.holderText = holderText;
        this.actualText = "";
        this.theme = theme;
        this.fillBackground = fillBackground;
        this.state = TextBoxState.INACTIVE;
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

    get text() {
        return this.actualText;
    }

    draw() {
        if (this.fillBackground || this.state === TextBoxState.ACTIVE) {
            this.applyBackgroundTheme();
            rect(this.x, this.y, this.width, this.height,
                 this.theme.background.round);
        }
        this.applyTextTheme();
        let string = this.holderText;
        textStyle(this.theme.holder.style);
        if (this.actualText !== "") {
            string = this.actualText;
            textStyle(this.theme.input.style);
        }
        text(" " + string, this.x, this.y, this.width, this.height);
    }

    applyBackgroundTheme() {
        fill(this.theme.background.fill);
        if (this.theme.background.stroke === null) {
            noStroke();
        } else {
            stroke(this.theme.background.stroke);
            strokeWeight(this.theme.background.strokeWeight);
        }
    }

    applyTextTheme() {
        let theme = this.theme.holder;
        if (this.state === TextBoxState.ACTIVE) {
            theme = this.theme.input;
        }
        textFont(theme.font);
        textSize(theme.size);
        fill(theme.fill);
        textAlign(LEFT, CENTER);
        stroke(theme.stroke);
        strokeWeight(theme.strokeWeight);
    }

    handleResize() {
        this.constraint.update();
    }

    handleKeyPressed(key, keyCode) {
        if (this.state === TextBoxState.ACTIVE) {
            if (keyCode === Constants.SPECIAL_KEYS.BACKSPACE) {
                this.actualText = this.actualText.slice(0, -1);
            }
        }
    }

    handleKeyTyped(key, keyCode) {
        if (this.state === TextBoxState.ACTIVE) {
            if (!(keyCode in Constants.SPECIAL_KEYS.VALUES)) {
                if (textWidth(this.actualText + "   ") < this.constraint.width) {
                    this.actualText += key;
                }
            }
        }
    }

    isClicked(x, y) {
        return this.x + this.width >= x && this.x <= x && this.y <= y
            && this.y + this.height >= y;
    }

    handleClick(x, y) {
        if (this.isClicked(x, y)) {
            if (this.state === TextBoxState.INACTIVE) {
                this.state = TextBoxState.ACTIVE;
            } else {
                this.state = TextBoxState.INACTIVE;
            }
        }
    }
}