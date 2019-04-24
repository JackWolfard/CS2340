import random


class Vector:
    @staticmethod
    def random(region):
        """
        Generates a random Vector within a region.

        :param region: A Region instance
        :return: A random Vector within the region
        """
        max_minus_min = region.max - region.min
        return region.min + Vector(random.randint(0, max_minus_min.x),
                                   random.randint(0, max_minus_min.y))

    def __init__(self, x, y):
        self.x = x
        self.y = y

    @property
    def mag(self):
        return (self.x ** 2 + self.y ** 2) ** (1 / 2)

    def area(self):
        return self.x * self.y

    def distance(self, other):
        return (self + other).mag

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __add__(self, other):
        return Vector(self.x + other.x, self.y + other.y)

    def __sub__(self, other):
        return Vector(self.x - other.x, self.y - other.y)

    def __mul__(self, other):
        return Vector(self.x * other, self.y * other)

    def __floordiv__(self, other):
        return Vector(self.x // other, self.y // other)

    def __repr__(self):
        return f"Vector({self.x}, {self.y})"
