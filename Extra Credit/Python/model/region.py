from model.vector import Vector

import random


class Region:
    @staticmethod
    def random(max_limit, min_size, max_size):
        min_limit = Vector(0, 0)
        region = Region(min_limit, max_limit)
        point = Vector.random(region)
        offset = Vector(random.randint(min_size, max_size),
                        random.randint(min_size, max_size))
        return Region(point, point + offset)

    def __init__(self, min, max):
        self.min = min
        self.max = max

    def center(self):
        return (self.max - self.min) // 2

    def area(self):
        return (self.max - self.min).area()

    def intersect(self, other):
        max_x = min(self.max.x, other.max.x)
        min_x = max(self.min.x, other.min.x)
        if max_x <= min_x:
            return Region(Vector(0, 0), Vector(0, 0))

        max_y = min(self.max.y, other.max.y)
        min_y = max(self.min.y, other.min.y)
        if max_y <= min_y:
            return Region(Vector(0, 0), Vector(0, 0))

        return Region(Vector(min_x, min_y), Vector(max_x, max_y))

    def __eq__(self, other):
        """
        A Region is equal to another region if the regions overlap by 50%
        """
        return self.intersect(other).area() / (self.area() + other.area()) >= 0.5

    def __repr__(self):
        return f"Region({self.min}, {self.max})"
