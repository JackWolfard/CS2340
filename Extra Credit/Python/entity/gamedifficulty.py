import enum


class GameDifficulty(enum.Enum):
    B = "Beginner"
    E = "Easy"
    N = "Normal"
    H = "Hard"
    I = "Impossible"

    @classmethod
    def to_list(cls):
        difficulties = []
        for difficulty in cls:
            difficulties.append(difficulty)
        return difficulties

    def __str__(self):
        return self.value
