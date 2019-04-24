class ActivityController:
    def __init__(self, term):
        self.term = term
        self.activity_stack = []

    @property
    def current_activity(self):
        return self.activity_stack[-1]

    def start_activity(self, activity):
        print(self.term.clear(), end="")
        self.activity_stack.append(activity(self.term, self))
        self.current_activity.start()

    def terminate_activity(self, res):
        cls = type(self.activity_stack.pop())
        if len(self.activity_stack) > 0:
            self.current_activity.resume(res, cls)
