from entity.shiptype import ShipType
from model.ship import Ship


class Player:
    def __init__(self, name, pilotPt, engPt, tradePt, fightPt, position):
        self.name = name
        self.pilotPt = pilotPt
        self.engPt = engPt
        self.tradePt = tradePt
        self.fightPt = fightPt
        self.ship = Ship(ShipType.GNAT)
        self.credits = 1000
        self.position = position

    @property
    def planet(self):
        return self.position.planet

    @property
    def solarsystem(self):
        return self.position.solarsystem

    @property
    def galaxy(self):
        return self.position.galaxy

    @property
    def location(self):
        return self.position.location

    @property
    def fuel(self):
        return self.ship.fuel_capacity

    @property
    def size(self):
        return self.ship.size

    @property
    def cargo_size(self):
        return self.ship.cargo_size

    def inventory_adapter(self, adapter):
        for i, item in enumerate(adapter):
            good = item[0]
            adapter[i].append(self.ship.get_quantity(good))

    def travel(self, solarsystem, planet, fuel_cost):
        if self.ship.fuel_capacity < fuel_cost:
            return False
        self.ship.fuel_capacity -= fuel_cost
        self.position.travel(solarsystem, planet)
        return True

    def sell(self, good, price):
        self.ship.remove_from_cargo(good)
        self.credits += price

    def buy(self, good, price):
        if price > self.credits:
            raise ValueError("Player does not have enough credits to buy good.")
        self.ship.add_to_cargo(good)
        self.credits -= price

    def __repr__(self):
        return (f"Player({self.name}, skills=" +
                f"{(self.pilotPt, self.engPt, self.tradePt, self.fightPt)})\n" +
                f"{self.position}")
