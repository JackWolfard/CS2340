from entity.names import Names
from model import model
from view.activity import Activity
from terminal.textbutton import TextButton


class PirateEncounterActivity(Activity):
    def start(self):
        print(self.term.clear(), end="")
        text = "Section ({}, {}) of {} Galaxy in {} Cluster nearby {}".format(
            model.game.location.x, model.game.location.y, model.game.galaxy.name,
            model.game.solarsystem.name, model.game.planet.name
        )
        print(self.banner(text))
        print()

        pirate, power = Names.PIRATE.random()
        text = "{} Approaches".format(pirate)
        print(self.term.center(self.term.bold_underline(text)))
        print()

        print("Power level: {}".format(power))
        print()

        print(self.term.underline("Action"))
        self.fight_button = TextButton(self.term, 0, 7, "Fight", "Fight")
        self.flee_button = TextButton(self.term, 0, 8, "Flee", "Flee")

        self._selection = 0
        self.fields = [self.fight_button, self.flee_button]

        with self.term.location(y=self.term.height):
            print(self.banner("Press 'ESC' to Go Back to Planet"), end="")

    @property
    def selection(self):
        return self.fields[self._selection]

    def draw(self):
        for field in self.fields:
            if field is not self.selection:
                field.draw()
            else:
                field.draw(blink=True)

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
                    if self.selection.choice == "Flee":
                        self.finish()

    def next_selection(self):
        self._selection = (self._selection + 1) % len(self.fields)
