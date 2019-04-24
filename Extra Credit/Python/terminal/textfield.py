class TextField:
    def __init__(self, term, x, y, holder_text, desc_text=None):
        self.term = term
        self.x = x
        self.y = y
        self.holder_text = holder_text
        self.input_text = None
        self.desc_text = desc_text

    def draw(self, blink=False):
        text = self.holder_text
        if self.input_text is not None:
            text = self.input_text
        with self.term.location(x=self.x, y=self.y):
            print(self.term.clear_eol(), end="")
            if self.desc_text:
                print(self.desc_text, end="")
            if blink:
                print(self.term.blink(text), end="")
            else:
                print(text, end="")

    def handle_key(self, key):
        if self.input_text is None:
            self.input_text = ""
        self.input_text += key

    def handle_delete(self):
        if self.input_text is not None:
            self.input_text = self.input_text[:-1]
            if self.input_text == "":
                self.input_text = None
