class Activity:
    def __init__(self, term, controller):
        self.term = term
        self.controller = controller

    def launch(self, activity):
        return self.controller.start_activity(activity)

    def start(self):
        pass

    def resume(self, ret, cls):
        pass

    def finish(self, res=None):
        self.controller.terminate_activity(res)

    def banner(self, text):
        line = text + " " * (self.term.width - len(text))
        return self.term.reverse(line)
