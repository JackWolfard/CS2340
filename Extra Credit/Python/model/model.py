# a singleton in Python can just be packaged as a module
# the global vars are properties and functions are methods
from model.game import Game

import pickle
import pathlib

game = None


def init_game(difficulty, name, pilot_pt, eng_pt, trader_pt, fighter_pt):
    global game
    game = Game(difficulty, name, pilot_pt, eng_pt, trader_pt, fighter_pt)


def travel_data():
    return sorted(game.galaxy.travel_map[game.planet.name], key=lambda x: x[2])


def market_data():
    adapter = game.marketplace.view_adapter()
    game.player.inventory_adapter(adapter)
    return sorted(adapter, key=lambda x: x[2])


def load_game():
    path = pathlib.Path("save.pickle")
    if path.exists():
        with path.open("rb") as f:
            global game
            game = pickle.load(f)


def resume_game():
    return game is not None


def save_game():
    if game is not None:
        path = pathlib.Path("save.pickle")
        with path.open("wb") as f:
            pickle.dump(game, f)
