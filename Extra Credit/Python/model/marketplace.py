from entity.goods import Goods

import random


class MarketPlace:
    def __init__(self, tech, resource):
        self.tech = tech
        self.resource = resource
        self.inventory = {}
        self.init_market()

    def init_market(self):
        for good in Goods:
            if good.min_level <= self.tech.level:
                quantity = random.randint(1, 4)
                if good.highest_tech_freq == self.tech.level:
                    quantity *= 2
                self.inventory[good] = MarketPlaceItem(quantity, good,
                                                       self.resource, self.tech)

    def sell_good(self, player, good):
        player.sell(good, self.inventory[good].sell_price)
        self.inventory[good].quantity += 1

    def buy_good(self, player, good):
        if self.inventory[good].quantity <= 0:
            raise IndexError("Good is out of stock in MarketPlace.")
        player.buy(good, self.inventory[good].buy_price)
        self.inventory[good].quantity -= 1

    def view_adapter(self):
        adapter = []
        for good in self.inventory:
            adapter.append([
                good, self.inventory[good].quantity,
                self.inventory[good].buy_price,
                self.inventory[good].sell_price
            ])
        return adapter


class MarketPlaceItem:
    def __init__(self, quantity, good, resource, tech):
        self.quantity = quantity
        self.buy_price = 0
        self.sell_price = 0
        self.calculate_pricing(good, resource, tech)

    def calculate_pricing(self, good, resource, tech):
        variance = random.randint(0, good.variance - 1)
        price = good.price + (good.level_increase *
                              (tech.level - good.min_level)) + variance
        if resource.id == good.increase_event:
            price *= 3
        elif resource.id == good.decrease_event:
            price //= 5
        elif resource.id == good.expensive_event:
            price = int(price * 1.5)    # floor result

        if price < good.min_price:
            price = good.min_price
        elif price > good.max_price:
            price = good.max_price

        self.buy_price = price
        self.sell_price = int(price * 0.9)
