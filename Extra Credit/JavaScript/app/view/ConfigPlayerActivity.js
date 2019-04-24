class ConfigPlayerActivity extends Activity {
    constructor() {
        super(ConfigPlayerViewModel);

        // header
        let constraint = new Constraint({top: 5}, {width: 45, height: 10},
                                        {horizontal: true, vertical: false});
        this.configPlayerTV = new TextView(constraint, "Configure Player",
                                                 Theme.HEADING_TEXT, true);

        // name
        constraint = new Constraint({top: 5, left: 5}, {width: 20, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.configPlayerTV.constraint});
        this.nameTView = new TextView(constraint, "Name", Theme.BODY_TEXT, true, false);

        constraint = new Constraint({top: 5, left: 5}, {width: 45, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.nameTView.constraint});
        this.nameTBox = new TextBox(constraint, "Name", Theme.TEXT_BOX);

        // point distributions
        constraint = new Constraint({top: 5}, {width: 90, height: 10},
                                    {horizontal: true, vertical: false},
                                    {top: this.nameTBox.constraint});
        this.distrTView = new TextView(constraint, "Distribution of Skill Points (max 16)",
                                       Theme.BODY_TEXT, true, false)

        // box to hold pts selection in
        // this.pointsConstraint = new Constraint({top: 5}, {width: 90, height: 50},
        //                             {horizontal: true, vertical: false},
        //                             {top: this.nameTBox.constraint});

        constraint = new Constraint({top: 5, left: 5}, {width: 20, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.distrTView.constraint});
        this.pilotTView = new TextView(constraint, "Pilot", Theme.BODY_TEXT, true, false);

        constraint = new Constraint({top: 5, left: 5}, {width: 10, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.distrTView.constraint,
                                     left: this.pilotTView.constraint});
        this.pilotTBox = new TextBox(constraint, "#", Theme.TEXT_BOX);

        constraint = new Constraint({top: 5, right: 5}, {width: 10, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.distrTView.constraint});
        this.engTBox = new TextBox(constraint, "#", Theme.TEXT_BOX);

        constraint = new Constraint({top: 5, right: 5}, {width: 20, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.distrTView.constraint,
                                     right: this.engTBox.constraint});
        this.engTView = new TextView(constraint, "Engineer", Theme.BODY_TEXT, true, false);


        constraint = new Constraint({top: 5, left: 5}, {width: 20, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.pilotTView.constraint});
        this.traderTView = new TextView(constraint, "Trader", Theme.BODY_TEXT, true, false);

        constraint = new Constraint({top: 5, left: 5}, {width: 10, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.pilotTView.constraint,
                                     left: this.traderTView.constraint});
        this.traderTBox = new TextBox(constraint, "#", Theme.TEXT_BOX);


        constraint = new Constraint({top: 5, right: 5}, {width: 10, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.engTView.constraint});
        this.fighterTBox = new TextBox(constraint, "#", Theme.TEXT_BOX);

        constraint = new Constraint({top: 5, right: 5}, {width: 20, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.engTView.constraint,
                                     right: this.fighterTBox.constraint});
        this.fighterTView = new TextView(constraint, "Fighter", Theme.BODY_TEXT, true, false);


        constraint = new Constraint({top: 5, left: 5}, {width: 30, height: 5},
                                    {horizontal: false, vertical: false},
                                    {top: this.traderTView.constraint});
        this.difficultyTView = new TextView(constraint, "Difficulty", Theme.BODY_TEXT, true, false)

        constraint = new Constraint({top: 5}, {width: 60, height: 5},
                                    {horizontal: true, vertical: false},
                                    {top: this.traderTView.constraint,
                                     left: this.difficultyTView.constraint});
        this.difficultyTBox = new TextBox(constraint, "Difficulty", Theme.TEXT_BOX);

        constraint = new Constraint({}, {width: 20, height: 5},
                                    {horizontal: true, vertical: true},
                                    {top: this.difficultyTBox.constraint});
        this.doneButton = new Button(constraint, "Done", Theme.DARK_BUTTON);

        this.textBoxes = [this.nameTBox, this.pilotTBox, this.engTBox,
                          this.traderTBox, this.fighterTBox,
                          this.difficultyTBox];
    }

    draw() {
        this.configPlayerTV.draw();
        this.nameTView.draw();
        this.nameTBox.draw();
        this.distrTView.draw();
        this.pilotTView.draw();
        this.pilotTBox.draw();
        this.engTView.draw();
        this.engTBox.draw();
        this.traderTView.draw();
        this.traderTBox.draw();
        this.fighterTView.draw();
        this.fighterTBox.draw();
        this.difficultyTView.draw();
        this.difficultyTBox.draw();
        this.doneButton.draw();
    }

    validateUserInput(res) {
        res.pilot = parseInt(res.pilot);
        res.eng = parseInt(res.eng);
        res.trader = parseInt(res.trader);
        res.fighter = parseInt(res.fighter);
        if (isNan(res.pilot) || isNaN(res.eng) || isNaN(res.trader)
            || isNaN(res.fighter)
            || res.pilot + res.eng + res.trader + res.fighter !== 16) {
            return false;
        }
        if (res.name.length === 0) {
            return false;
        }
    }

    toastBadUserInput() {

    }

    handleMouseReleased(x, y) {
        if (this.doneButton.handleClick(x, y)) {
            let res = {}
            res.name = this.nameTBox.text;
            res.pilot = this.pilotTBox.text;
            res.eng = this.engTBox.text;
            res.trader = this.traderTBox.text;
            res.fighter = this.fighterTBox.text;
            res.difficulty = this.difficultyTBox.text;

            res = this.validateUserInput(res);
            if (res !== false) {
                // this.finish(res);
            } else {
                // this.toastBadUserInput();
            }
        }
    }

    handleMousePressed(x, y) {
        // for animations
        this.doneButton.handleClick(x, y);
    }

    handleMouseClicked(x, y) {
        for (let textBox of this.textBoxes) {
            textBox.handleClick(x, y);
        }
    }

    handleKeyPressed(key, keyCode) {
        for (let textBox of this.textBoxes) {
            textBox.handleKeyPressed(key, keyCode);
        }
    }

    handleKeyTyped(key, keyCode) {
        for (let textBox of this.textBoxes) {
            textBox.handleKeyTyped(key, keyCode);
        }
    }

    handleResize() {
        this.configPlayerTV.handleResize();
        this.nameTView.handleResize();
        this.nameTBox.handleResize();
        this.distrTView.handleResize();
        this.pilotTView.handleResize();
        this.pilotTBox.handleResize();
        this.engTView.handleResize();
        this.engTBox.handleResize();
        this.traderTView.handleResize();
        this.traderTBox.handleResize();
        this.fighterTView.handleResize();
        this.fighterTBox.handleResize();
        this.difficultyTView.handleResize();
        this.difficultyTBox.handleResize();
        this.doneButton.handleResize();
    }
}