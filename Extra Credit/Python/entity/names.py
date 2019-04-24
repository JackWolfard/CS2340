import enum
import random


class Names(enum.Enum):
    PLANET = ["Deshaan", "Kumuro", "Cho", "Kepler", "Seyda", "Veela", "Haru",
              "Khor", "Babka", "Noi", "Earth", "Jupiter", "Uranus", "Chaldene",
              "Hoth", "Unerth", "Volmarvis", "Ciri 70I", "Lorth 212",
              "Naboo", "Tatooine", "Dagobah", "Alderaan", "Kashyyyk",
              "Nal Hutta", "Ruusan", "Lotho Minor", "Jedha", "Felucia",
              "Endor", "Da Soocha V", "J't'p'tan", "N'zoth", "Bakura"]
    SOLARSYSTEM = ["Kepler-37", "Gamma Librae", "Upsilon Andromedae",
                   "Rho Coronae Borealis", "Pi Mensae", "Ursae Majoris",
                   "Cancri", "Sun", "Csilla", "Tython", "Kuat", "Hapes", "Lah'mu"]
    GALAXY = ["Milky Way", "Messier 63", "Whirlpool", "Andromeda", "Tadpole",
              "Cygnus A"]

    def random(self):
        return random.choice(self.value)
