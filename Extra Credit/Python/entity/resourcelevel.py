import enum
import random


class ResourceLevel(enum.Enum):
    NONE = "NOSPECIALRESOURCES", "No Special Resources"
    M_RICH = "MINERALRICH", "Mineral Rich"
    M_POOR = "MINERALPOOR", "Mineral Poor"
    DESERT = "DESERT", "Desert"
    WATER = "LOTSOFWATER", "Lots of Water"
    S_RICH = "RICHSOIL", "Rich Soil"
    S_POOR = "POORSOIL", "Poor Soil"
    F_RICH = "RICHFAUNA", "Rich Fauna"
    LIFELESS = "LIFELESS", "Lifeless"
    SHROOMS = "WEIRDMUSHROOMS", "Weird Mushrooms"
    HERBS = "LOTSOFHERBS", "Lots of Herbs"
    ART = "ARTISTIC", "Artistic"
    WAR = "WARLIKE", "Warlike"

    def __init__(self, id, full_name):
        self.id = id
        self.full_name = full_name

    def __str__(self):
        return self.full_name

    @classmethod
    def random(cls):
        resources = []
        for resource in cls:
            resources.append(resource)
        return random.choice(resources)
