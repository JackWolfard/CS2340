from entity import constants
from entity.names import Names
from model.region import Region
from model.vector import Vector
from model.solarsystem import SolarSystem

import random


class Galaxy:
    @staticmethod
    def generate_systems():
        systems = []
        num_systems = random.randint(constants.MIN_SOLARSYSTEMS,
                                     constants.MAX_SOLARSYSTEMS)
        while len(systems) < num_systems:
            max_limit = Vector(constants.MAX_UNIVERSE_X,
                               constants.MAX_UNIVERSE_Y)
            rand_region = Region.random(max_limit,
                                        constants.MIN_SOLARSYSTEM_SIZE,
                                        constants.MAX_SOLARSYSTEM_SIZE)
            system = SolarSystem(rand_region)
            if system not in systems:
                systems.append(system)
        return systems

    def __init__(self, name=None, systems=None):
        if name is None:
            name = Names.GALAXY.random()
        self.name = name
        if systems is None:
            systems = Galaxy.generate_systems()
        self.systems = systems
        self.generate_travel_distances()

    def generate_travel_distances(self):
        travel_map = {}
        for system in self.systems:
            for planet in system.planets:
                if planet.name not in travel_map:
                    travel_map[planet.name] = []
                for dest_system in self.systems:
                    for dest_planet in dest_system.planets:
                        if planet is not dest_planet:
                            dist = planet.distance(dest_planet)
                            travel_map[planet.name].append((dest_system,
                                                            dest_planet, dist))
        self.travel_map = travel_map

    def __repr__(self):
        return f"Galaxy({self.name}, num_systems={len(self.systems)})"
