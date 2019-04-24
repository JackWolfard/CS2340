class TextView {
    constructor(constraint, text, theme, fillBackground=false, dynamicResize=true) {
        this.constraint = constraint;
        this.text = text;
        this.theme = theme;
        this.fillBackground = fillBackground;
        this.dynamicResize = dynamicResize;
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

    get fontSize() {
        if (this.dynamicResize) {
            return Math.floor(Math.min(this.constraint.height,
                                       this.constraint.width / this.text.length));
        }
        return this.theme.size;
    }

    draw() {
        if (this.fillBackground) {
            this.applyBackgroundTheme();
            rect(this.x, this.y, this.width, this.height,
                 this.theme.background.round);
        }
        this.applyTextTheme();
        text(" " + this.text, this.x, this.y, this.width, this.height);
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
        textStyle(this.theme.style);
        textFont(this.theme.font);
        textSize(this.fontSize);
        fill(this.theme.fill);
        textAlign(CENTER, CENTER);
        stroke(this.theme.stroke);
        strokeWeight(this.theme.strokeWeight);
    }

    handleResize() {
        this.constraint.update();
    }
}