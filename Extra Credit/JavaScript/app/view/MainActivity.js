class MainActivity extends Activity {
    constructor() {
        super(MainViewModel);
        let constraint = new Constraint({top: 5}, {width: 35, height: 10},
                                        {horizontal: true, vertical: false});
        this.textView = new TextView(constraint, "SPACETRADER", Theme.HEADING_TEXT, true);

        constraint = new Constraint({top: 40, left: 10}, {width: 25, height: 10},
            {horizontal: false, vertical: false}, {top: this.textView.constraint});
        this.newGameButton = new Button(constraint, "New Game", Theme.DARK_BUTTON);

        constraint = new Constraint({top: 5, left: 10}, {width: 25, height: 10},
            {horizontal: false, vertical: false}, {top: this.newGameButton.constraint});
        this.resumeGameButton = new Button(constraint, "Resume Game", Theme.DARK_BUTTON);

        constraint = new Constraint({top: 40, right: 10}, {width: 25, height: 10},
            {horizontal: false, vertical: false}, {top: this.textView.constraint});
        this.loadGameButton = new Button(constraint, "Load Game", Theme.DARK_BUTTON);

        constraint = new Constraint({top: 5, right: 10}, {width: 25, height: 10},
            {horizontal: false, vertical: false}, {top: this.loadGameButton.constraint});
        this.saveGameButton = new Button(constraint, "Save Game", Theme.DARK_BUTTON);

        this.buttons = [this.newGameButton, this.resumeGameButton,
                        this.loadGameButton, this.saveGameButton];
    }

    draw() {
        for (let button of this.buttons) {
            button.draw();
        }
        this.textView.draw();
    }

    handleMouseReleased(x, y) {
        for (let button of this.buttons) {
            button.handleClick(x, y);
        }
    }

    handleMousePressed(x, y) {
        // for animations
        for (let button of this.buttons) {
            button.handleClick(x, y);
        }
    }

    handleResize() {
        for (let button of this.buttons) {
            button.handleResize();
        }
        this.textView.handleResize();
    }
}