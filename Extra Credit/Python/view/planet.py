from model import model
from view.activity import Activity
from view.marketplace import MarketPlaceActivity
from view.pirateencounter import PirateEncounterActivity
from view.travel import TravelActivity
from terminal.textbutton import TextButton


import random


class PlanetActivity(Activity):
    def start(self):
        text = "Section ({}, {}) of {} Galaxy in {} Cluster".format(
            model.game.location.x, model.game.location.y, model.game.galaxy.name,
            model.game.solarsystem.name
        )
        print(self.banner(text))
        print()
        text = "Planet {}".format(model.game.planet.name)
        print(self.term.center(self.term.bold_underline(text)))
        print()
        print("Technology Level: {}".format(model.game.planet.tech))
        print("Resource Level: {}".format(model.game.planet.resource))
        with self.term.location(y=self.term.height):
            print(self.banner("Press 'ESC' to Go Back to Main Menu"), end="")

        self.marketplace_button = TextButton(self.term, 0, 8,
                                             MarketPlaceActivity, "Marketplace")
        self.travel_button = TextButton(self.term, 0, 9,
                                        TravelActivity, "Travel")

        self.fields = [
            self.marketplace_button, self.travel_button
        ]

        self._selection = 0

    @property
    def selection(self):
        return self.fields[self._selection]

    def resume(self, ret, cls):
        print(self.term.clear(), end="")
        if cls is TravelActivity:
            if random.random() > 0.5:
                self.launch(PirateEncounterActivity)
                return
        self.start()

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
                    self.launch(self.selection.choice)

    def next_selection(self):
        self._selection = (self._selection + 1) % len(self.fields)
