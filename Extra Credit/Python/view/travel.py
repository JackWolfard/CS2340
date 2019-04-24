from model import model
from view.activity import Activity
from terminal.textbutton import TextButton

from texttable import Texttable


class TravelActivity(Activity):
    def start(self):
        text = "Section ({}, {}) of {} Galaxy in {} Cluster on {}".format(
            model.game.location.x, model.game.location.y, model.game.galaxy.name,
            model.game.solarsystem.name, model.game.planet.name
        )
        print(self.banner(text))
        print()

        text = "Travel".format(model.game.player.fuel)
        print(self.term.center(self.term.bold_underline(text)))
        print()

        text = "Fuel Remaining: {}".format(model.game.player.fuel)
        print(text)
        print()

        table = Texttable(max_width=self.term.width)
        table.set_deco(Texttable.HEADER)
        table.set_cols_align(["l", "l", "l", "r"])
        table.set_cols_valign(["m", "m", "m", "m"])
        headers = [["#", "Solar System", "Planet", "Fuel Cost"]]
        data = model.travel_data()[:self.term.height - 10]
        clean_data = []
        for item in data:
            clean_data.append([" ", item[0].name, item[1].name, item[2]])
        table.add_rows(headers + clean_data)
        text = table.draw()
        print(text)

        with self.term.location(y=self.term.height):
            print(self.banner("Press 'ESC' to Go Back to Planet"), end="")

        self.fields = self.generate_buttons(data, 1, 8)

        self._selection = 0

    def generate_buttons(self, data, x, y):
        fields = []
        for datum in data:
            fields.append(TextButton(self.term, x, y, datum, "*"))
            y += 1
        return fields

    @property
    def selection(self):
        return self.fields[self._selection]

    def resume(self, ret, cls):
        print(self.term.clear(), end="")
        self.start()

    def draw(self):
        for field in self.fields:
            if field is not self.selection:
                field.draw(text=" ", clear=False)
            else:
                field.draw(blink=True, clear=False)

    def update(self):
        self.draw()
        with self.term.cbreak():
            val = self.term.inkey()
            if val.is_sequence:
                if val.name == "KEY_ESCAPE":
                    self.finish()
                elif val.name == "KEY_TAB":
                    self.next_selection()
                elif val.name == "KEY_ENTER":
                    res = model.game.travel(*self.selection.choice)
                    if res:
                        self.finish()

    def next_selection(self):
        self._selection = (self._selection + 1) % len(self.fields)
