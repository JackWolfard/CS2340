from model import model
from view.activity import Activity
from terminal.textbutton import TextButton
from terminal.textchoice import TextChoice


from texttable import Texttable


class MarketPlaceActivity(Activity):
    def start(self):
        data = self.refresh()
        self.action_choice = TextChoice(self.term, 0, 7,
                                        ["Buy", "Sell"], "Mode: ")

        self.fields = [self.action_choice] + self.generate_buttons(data, 1, 11)

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
                if type(field) is TextButton:
                    field.draw(text=" ", clear=False)
                else:
                    field.draw()
            else:
                if type(field) is TextButton:
                    field.draw(blink=True, clear=False)
                else:
                    field.draw(blink=True)

    def refresh(self):
        print(self.term.clear(), end="")
        text = "Section ({}, {}) of {} Galaxy in {} Cluster on {}".format(
            model.game.location.x, model.game.location.y, model.game.galaxy.name,
            model.game.solarsystem.name, model.game.planet.name
        )
        print(self.banner(text))
        print()

        text = "Marketplace".format(model.game.player.fuel)
        print(self.term.center(self.term.bold_underline(text)))
        print()

        text = ("Credit: {}".format(model.game.player.credits) +
                "\nShip Cargo: {}/{}".format(
                    model.game.player.size, model.game.player.cargo_size
        ))
        print(text)
        print()
        print("\n")

        table = Texttable(max_width=self.term.width)
        table.set_deco(Texttable.HEADER)
        table.set_cols_align(["l", "l", "r", "r", "r", "r"])
        table.set_cols_valign(["m", "m", "m", "m", "m", "m"])
        headers = [["#", "Good", "Quantity", "Buy Price", "Sell Price", "Owned"]]
        data = model.market_data()[:self.term.height - 13]
        clean_data = []
        for item in data:
            clean_data.append([" ", item[0].full_name, item[1], item[2],
                               item[3], item[4]])
        table.add_rows(headers + clean_data)
        text = table.draw()
        print(text)

        with self.term.location(y=self.term.height):
            print(self.banner("Press 'ESC' to Go Back to Planet"), end="")
        return data

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
                    if type(self.selection) is TextButton:
                        if self.action_choice.choice == "Buy":
                            res = model.game.buy(self.selection.choice[0])
                            if res:
                                self.refresh()
                        else:
                            res = model.game.sell(self.selection.choice[0])
                            if res:
                                self.refresh()
                elif self.selection is not None and \
                        type(self.selection) is TextChoice:
                    if val.name == "KEY_RIGHT" or val.name == "KEY_LEFT":
                        self.selection.handle_key(val.name)

    def next_selection(self):
        self._selection = (self._selection + 1) % len(self.fields)
