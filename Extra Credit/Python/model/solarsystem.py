from entity.names import Names
from entity import constants
from model.planet import Planet

import random


class SolarSystem:
    @staticmethod
    def generate_planets(region):
        planets = []
        num_planets = random.randint(constants.MIN_PLANETS,
                                     constants.MAX_PLANETS)
        while len(planets) < num_planets:
            planet = Planet(region)
            if planet not in planets:
                planets.append(planet)
        return planets

    def __init__(self, region, name=None, planets=None):
        if name is None:
            name = Names.SOLARSYSTEM.random()
        self.name = name
        self.region = region
        if planets is None:
            planets = SolarSystem.generate_planets(region)
        self.planets = planets

    @property
    def location(self):
        return self.region.center()

    def __eq__(self, other):
        return self.name == other.name or self.region == other.region

    def __repr__(self):
        return (f"SolarSystem({self.name}, region={self.region}, " +
                f"num_planets={len(self.planets)})")
