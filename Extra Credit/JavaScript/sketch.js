let player;
let activityController;
let Theme;

function setup() {
	createCanvas(windowWidth, windowHeight);
    sourceCodePro = ""
    textFont("Source Code Pro");
    Theme = Object.freeze({
        BACKGROUND_COLOR: "#30343f",
        DARK_BUTTON: Object.freeze({
            [ButtonState.DEPRESSED]: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#71969b",
                round: 10,
                text: Object.freeze({
                    stroke: 40,
                    strokeWeight: 4,
                    fill: "#4b8f8c",
                    style: NORMAL,
                    size: 24,
                    font: "Source Code Pro"
                })
            }),
            [ButtonState.PRESSED]: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#4b8f8c",
                round: 10,
                text: Object.freeze({
                    stroke: 40,
                    strokeWeight: 4,
                    fill: "#71969b",
                    style: NORMAL,
                    size: 24,
                    font: "Source Code Pro"
                })
            })
        }),
        HEADING_TEXT: Object.freeze({
            stroke: 40,
            strokeWeight: 4,
            fill: "#71969b",
            style: BOLD,
            size: 60,
            font: "Source Code Pro",
            background: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#4b8f8c",
                round: 10
            })
        }),
        BODY_TEXT: Object.freeze({
            stroke: 40,
            strokeWeight: 4,
            fill: "#71969b",
            style: NORMAL,
            size: 24,
            font: "Source Code Pro",
            background: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#4b8f8c",
                round: 10
            })
        }),
        TEXT_BOX: Object.freeze({
            input: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#71969b",
                style: NORMAL,
                size: 40,
                font: "Source Code Pro",
            }),
            holder: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#71969b",
                style: ITALIC,
                size: 40,
                font: "Source Code Pro"
            }),
            background: Object.freeze({
                stroke: 40,
                strokeWeight: 4,
                fill: "#4b8f8c",
                round: 10
            })
        })
    })

    Constants.SPECIAL_KEYS.BACKSPACE = BACKSPACE;
    Constants.SPECIAL_KEYS.DELETE = DELETE;
    Constants.SPECIAL_KEYS.ENTER = ENTER;
    Constants.SPECIAL_KEYS.RETURN = RETURN;
    Constants.SPECIAL_KEYS.TAB = TAB;
    Constants.SPECIAL_KEYS.ESCAPE = ESCAPE;
    Constants.SPECIAL_KEYS.SHIFT = SHIFT;
    Constants.SPECIAL_KEYS.CONTROL = CONTROL;
    Constants.SPECIAL_KEYS.OPTION = OPTION;
    Constants.SPECIAL_KEYS.ALT = ALT;
    Constants.SPECIAL_KEYS.UP_ARROW = UP_ARROW;
    Constants.SPECIAL_KEYS.DOWN_ARROW = DOWN_ARROW;
    Constants.SPECIAL_KEYS.LEFT_ARROW = LEFT_ARROW;
    Constants.SPECIAL_KEYS.RIGHT_ARROW = RIGHT_ARROW;

    Constants.SPECIAL_KEYS.VALUES = [BACKSPACE, DELETE, ENTER, RETURN, TAB,
                                     ESCAPE, SHIFT, CONTROL, OPTION, ALT,
                                     UP_ARROW, DOWN_ARROW, LEFT_ARROW,
                                     RIGHT_ARROW];

    Object.freeze(Constants.SPECIAL_KEYS);

    activityController = new ActivityController(MainActivity);
    player = new Player("Jack", 1, 2, 3, 4);
    console.log(player);
}

function draw() {
    background(Theme.BACKGROUND_COLOR);
    activityController.update();
    activityController.draw();
}

function mousePressed() {
    // mouseX, mouseY are used for the location of the mouse
    activityController.handleMousePressed(mouseX, mouseY);
}

function mouseReleased() {
    activityController.handleMouseReleased(mouseX, mouseY);
}

function mouseClicked() {
    activityController.handleMouseClicked(mouseX, mouseY);
}

function keyPressed() {
    activityController.handleKeyPressed(key, keyCode);
}

function keyTyped() {
    activityController.handleKeyTyped(key, keyCode)
}

function windowResized() {
    resizeCanvas(windowWidth, windowHeight);
    activityController.handleResize();
}