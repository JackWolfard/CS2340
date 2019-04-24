class TextChoice:
    def __init__(self, term, x, y, choices, desc_text=None):
        self.term = term
        self.x = x
        self.y = y
        self.choices = choices
        self._choice = 0
        self.desc_text = desc_text

    @property
    def choice(self):
        return self.choices[self._choice]

    def draw(self, blink=False):
        with self.term.location(x=self.x, y=self.y):
            print(self.term.clear_eol(), end="")
            text = str(self.choices[self._choice])
            if self.desc_text:
                print(self.desc_text, end="")
            if blink:
                print(self.term.blink(text), end="")
            else:
                print(text, end="")

    def handle_key(self, key):
        if key == "KEY_RIGHT":
            self._choice = (self._choice + 1) % len(self.choices)
        elif key == "KEY_LEFT":
            self._choice = (self._choice - 1) % len(self.choices)
