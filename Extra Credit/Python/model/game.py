from model.galaxy import Galaxy
from model.player import Player
from model.position import Position


class Game:

    def __init__(self, difficulty, name, pilotPt, engPt, tradePt, fightPt):
        self.difficulty = difficulty
        self.galaxy = Galaxy()
        self.player = Player(name, pilotPt, engPt, tradePt, fightPt,
                             Position(self.galaxy))

    @property
    def planet(self):
        return self.player.planet

    @property
    def marketplace(self):
        return self.planet.marketplace

    @property
    def solarsystem(self):
        return self.player.solarsystem

    @property
    def location(self):
        return self.player.location

    def buy(self, good):
        try:
            self.marketplace.buy_good(self.player, good)
        except (ValueError, IndexError):
            return False
        return True

    def sell(self, good):
        try:
            self.marketplace.sell_good(self.player, good)
        except ValueError:
            return False
        return True

    def travel(self, *args, **kwargs):
        return self.player.travel(*args, **kwargs)

    def __repr__(self):
        return "Game with Player: {}, Difficulty: {}.".format(self.player,
                                                              self.difficulty)
