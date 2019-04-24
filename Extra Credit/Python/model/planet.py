from entity.names import Names
from entity.resourcelevel import ResourceLevel
from entity.techlevel import TechLevel
from model.marketplace import MarketPlace
from model.vector import Vector


class Planet:
    def __init__(self, region, name=None):
        if name is None:
            name = Names.PLANET.random()
        self.name = name
        self.tech = TechLevel.random()
        self.resource = ResourceLevel.random()
        self.marketplace = MarketPlace(self.tech, self.resource)
        self.location = Vector.random(region)

    def distance(self, other):
        return self.location.distance(other.location)

    def __eq__(self, other):
        return self.location == other.location or self.name == other.name

    def __repr__(self):
        return (f"Planet({self.name}, location={self.location}, " +
                f"tech={self.tech}, resource={self.resource})")
