class ActivityController {
    constructor(mainActivityClass) {
        this.activityStack = new Array();
        // the mainActivity will always be on the stack
        Activity.controller = this;
        this.startActivity(mainActivityClass);
    }

    get currentActivity() {
        return this.activityStack[this.activityStack.length - 1];
    }

    startActivity(activityClass) {
        if (this.activityStack.length > 0) {
            this.currentActivity.pause();
        }
        this.activityStack.push(new activityClass());
        this.currentActivity.start();
    }

    terminateActivity(result) {
        if (this.activityStack.length !== 1) {
            this.currentActivity.terminate();
            this.activityStack.pop();
            this.currentActivity.resume(result);
        } else {
            throw "Cannot terminate main activity.";
        }
    }

    update() {
        this.currentActivity.update();
    }

    draw() {
        this.currentActivity.draw();
    }

    handleMousePressed(x, y) {
        this.currentActivity.handleMouseReleased(x, y);
    }

    handleMouseReleased(x, y) {
        this.currentActivity.handleMouseReleased(x, y);
    }

    handleMouseClicked(x, y) {
        this.currentActivity.handleMouseClicked(x, y);
    }

    handleResize() {
        this.currentActivity.handleResize();
    }

    handleKeyPressed(key, keyCode) {
        this.currentActivity.handleKeyPressed(key, keyCode);
    }

    handleKeyTyped(key, keyCode) {
        this.currentActivity.handleKeyTyped(key, keyCode);
    }
}