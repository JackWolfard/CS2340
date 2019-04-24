class Ship:
    def __init__(self, _type, name=None):
        self.type = _type
        if name is None:
            name = self.type.full_name
        self.name = name
        self.fuel_capacity = _type.fuel_capacity
        self.size = 0
        self.inventory = {}

    @property
    def cargo_size(self):
        return self.type.cargo_size

    def get_quantity(self, good):
        return self.inventory.get(good, 0)

    def add_to_cargo(self, item):
        if self.size >= self.cargo_size:
            raise IndexError("Cargo is already full.")
        self.inventory[item] = self.inventory.get(item, 0) + 1
        self.size += 1

    def remove_from_cargo(self, item):
        if item not in self.inventory:
            raise ValueError("{} does not exist in cargo.".format(item))
        self.inventory[item] -= 1
        if self.inventory[item] == 0:
            del self.inventory[item]
        self.size -= 1

    def __repr__(self):
        return "{} of model {}".format(self.name, self.type)
