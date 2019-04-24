import enum


class ShipType(enum.Enum):
    GNAT = ("Gnat", 150, 15)
    FLEA = ("Flea", 5, 0)

    def __init__(self, full_name, fuel_capacity, cargo_size):
        self.full_name = full_name
        self.fuel_capacity = fuel_capacity
        self.cargo_size = cargo_size
