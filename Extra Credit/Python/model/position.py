class Position:
    def __init__(self, galaxy, solarsystem=None, planet=None):
        self.galaxy = galaxy
        if solarsystem is None:
            solarsystem = self.galaxy.systems[0]
        self.solarsystem = solarsystem
        if planet is None:
            planet = self.solarsystem.planets[0]
        self.planet = planet

    @property
    def location(self):
        return self.planet.location

    def travel(self, solarsystem, planet):
        self.solarsystem = solarsystem
        self.planet = planet

    def __repr__(self):
        return (f"Position({self.galaxy}, {self.solarsystem}, {self.planet})")
