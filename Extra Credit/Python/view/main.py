from view.activity import Activity
from view.configure import ConfigActivity
from view.planet import PlanetActivity
from model import model

import time


class MainActivity(Activity):
    def start(self):
        for i in range((self.term.height // 2) - 5):
            print(self.term.clear())
            with self.term.location(y=i):
                print(self.term.center(self.term.bold_underline("Spacetrader")))
            time.sleep(0.05)
        pos_x = self.term.move_x(self.term.width // 2 - 10)
        print(self.term.move_y(i + 2) + pos_x + "1. New Game")
        print(pos_x + "2. Load Game")
        print(pos_x + "3. Resume Game")
        print(pos_x + "4. Save Game")
        with self.term.location(y=self.term.height):
            print(self.banner("Press 'Q' to Quit"), end="")

    def resume(self, res, cls):
        if cls is ConfigActivity and res is not False:
            model.init_game(res["difficulty"], res["name"], res["pilot_pt"],
                            res["eng_pt"], res["trader_pt"], res["fighter_pt"])
            self.launch(PlanetActivity)
        else:
            self.start()

    def update(self):
        with self.term.cbreak():
            val = self.term.inkey()
            if val == "1":
                self.launch(ConfigActivity)
            elif val == "2":
                model.load_game()
            elif val == "3":
                res = model.resume_game()
                if res:
                    self.launch(PlanetActivity)
            elif val == "4":
                model.save_game()
            elif val.lower() == "q":
                self.finish()
