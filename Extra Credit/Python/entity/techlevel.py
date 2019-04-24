import enum
import random


class TechLevel(enum.Enum):
    PRE_AG = ("Pre-Agriculture", 0)
    AGR = ("Agriculture", 1)
    MED = ("Medieval", 2)
    REN = ("Renaissance", 3)
    EARLY_IND = ("Early Industrial", 4)
    IND = ("Industrial", 5)
    POST_IND = ("Post-Industrial", 6)
    HITECH = ("Hi-Tech", 7)

    def __init__(self, full_name, level):
        self.full_name = full_name
        self.level = level

    def __str__(self):
        return self.full_name

    @classmethod
    def random(cls):
        techs = []
        for tech in cls:
            techs.append(tech)
        return random.choice(techs)
