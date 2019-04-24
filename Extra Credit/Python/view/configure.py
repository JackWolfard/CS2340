from view.activity import Activity
from terminal.textfield import TextField
from terminal.textchoice import TextChoice
from entity.gamedifficulty import GameDifficulty

import time


class ConfigActivity(Activity):
    def start(self):
        self.name_field = TextField(self.term, 0, 3, "Enter Name", "Name: ")
        self.pilot_field = TextField(self.term, 0, 6, "#", "Pilot Points: ")
        self.eng_field = TextField(self.term, 0, 7, "#", "Engineer Points: ")
        self.trader_field = TextField(self.term, 0, 8, "#", "Trader Points: ")
        self.fighter_field = TextField(self.term, 0, 9, "#", "Fighter Field: ")
        self.difficulty_choice = TextChoice(self.term, 0, 11,
                                            GameDifficulty.to_list(),
                                            "Difficulty: ")

        self.fields = [
            self.name_field, self.pilot_field, self.eng_field,
            self.trader_field, self.fighter_field, self.difficulty_choice
        ]

        self.selection = 0

        print(self.term.center(self.term.bold_underline("Configure Player")))
        with self.term.location(y=5):
            print(self.term.underline("Distribution of Points"))
        with self.term.location(y=self.term.height):
            print(self.banner("Press 'ENTER' to Create Character or 'ESC' to " +
                              "Go Back to Main Menu"), end="")

    def draw(self):
        for field in self.fields:
            if self.selection is None or field is not self.fields[self.selection]:
                field.draw()
            else:
                field.draw(blink=True)

    def next_selection(self):
        if self.selection is None:
            self.selection = 0
        else:
            self.selection += 1
            if len(self.fields) <= self.selection:
                self.selection = None

    def parse_input(self):
        try:
            res = {
                'name': self.name_field.input_text,
                'pilot_pt': int(self.pilot_field.input_text),
                'eng_pt': int(self.eng_field.input_text),
                'trader_pt': int(self.trader_field.input_text),
                'fighter_pt': int(self.fighter_field.input_text),
                'difficulty': self.difficulty_choice.choice
            }
            total = 0
            for key in res:
                if res[key] is None:
                    return False
                if type(res[key]) is int:
                    total += res[key]
            if total != 16:
                return False
            return res
        except:
            return False

    def update(self):
        self.draw()
        with self.term.cbreak():
            val = self.term.inkey()
            if val.is_sequence:
                if val.name == "KEY_ESCAPE":
                    self.finish(False)
                elif val.name == "KEY_TAB":
                    self.next_selection()
                elif val.name == "KEY_ENTER":
                    res = self.parse_input()
                    if res:
                        self.finish(res)
                elif val.name == "KEY_DELETE":
                    if self.selection is not None:
                        self.fields[self.selection].handle_delete()
                elif self.selection is not None and \
                        type(self.fields[self.selection]) is TextChoice:
                    if val.name == "KEY_RIGHT" or val.name == "KEY_LEFT":
                        self.fields[self.selection].handle_key(val.name)
            else:
                if self.selection is not None:
                    self.fields[self.selection].handle_key(val)
