class Player {
    constructor(player) {
        this.name = player.name;
        this.pilotPt = player.pilotPt;
        this.engPt = player.engPt;
        this.tradePt = player.tradePt;
        this.fightPt = player.fightPt;
        this.credits = 1000;
        this.ship = new Ship(ShipType.GNAT);
        this.location = {
            planet: null,
            get solarSystem() { return this.planet.solarSystem },
            get universe() { return this.solarSystem.universe }
        }
    }
}