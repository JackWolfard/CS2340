class TextButton:
    def __init__(self, term, x, y, choice, text):
        self.term = term
        self.x = x
        self.y = y
        self.choice = choice
        self.text = text

    def draw(self, blink=False, text=None, clear=True):
        if text is None:
            text = self.text
        with self.term.location(x=self.x, y=self.y):
            if clear:
                print(self.term.clear_eol(), end="")
            if blink:
                print(self.term.blink(text), end="")
            else:
                print(text, end="")
