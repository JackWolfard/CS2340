import enum


class Goods(enum.Enum):
    FIREARMS = ("Firearms", 1250, 3, 5, -75, 100, "WAR", "WARLIKE", None, 600, 1100)
    FOOD = ("Food", 100, 1, 1, 5, 5, "CROPFAIL", "RICHSOIL", "POORSOIL", 90, 160)
    FURS = ("Furs", 250, 0, 0, 10, 10, "COLD", "RICHFAUNA", "LIFELESS", 230, 280)
    GAMES = ("Games", 250, 3, 6, -10, 5, "BOREDOM", "ARTISTIC", None, 160, 270)
    ORE = ("Ore", 350, 2, 3, 20, 10, "WAR", "MINERALRICH", "MINERALPOOR", 350, 420)
    NARCOTICS = ("Narcotics", 3500, 5, 5, -125, 150, "BOREDOM", "WEIRDMUSHROOMS", None, 2000, 3000)
    MACHINES = ("Machines", 900, 4, 5, -30, 5, "LACKOFWORKERS", None, None, 600, 800)
    MEDICINE = ("Medicine", 650, 4, 6, -20, 10, "PLAGUE", "LOTSOFHERBS", None, 400, 700)
    ROBOTS = ("Robots", 5000, 6, 7, -150, 100, "LACKOFWORKERS", None, None, 3500, 5000)
    WATER = ("Water", 30, 0, 2, 3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50)

    def __init__(self, full_name, price, min_level, highest_tech_freq,
                 level_increase, variance, increase_event, decrease_event,
                 expensive_event, min_price, max_price):
        self.full_name = full_name
        self.price = price
        self.min_level = min_level
        self.highest_tech_freq = highest_tech_freq
        self.level_increase = level_increase
        self.variance = variance
        self.increase_event = increase_event
        self.decrease_event = decrease_event
        self.expensive_event = expensive_event
        self.min_price = min_price
        self.max_price = max_price
