class Activity {
    // static property controller gets initialized by ActivityController
    // used for finishing an activity

    constructor(ViewModelClass) {
        this.viewModel = new ViewModelClass();
    }

    start() {

    }

    pause() {

    }

    resume() {

    }

    terminate() {

    }

    finish(result=null) {
        this.controller.terminateActivity(result);
    }

    update() {

    }

    draw() {

    }

    handleMouseReleased(x, y) {

    }

    handleMousePressed(x, y) {

    }

    handleMouseClicked(x, y) {

    }

    handleResize() {

    }

    handleKeyPressed(key, keyCode) {

    }

    handleKeyTyped(key, keyCode) {

    }
}