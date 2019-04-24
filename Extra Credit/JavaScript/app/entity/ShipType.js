/**
 * private class to construct Enum objects for ShipType
 *
 * instances will be frozen after construction
 */
class _ShipType {
    constructor(name, cargoCapacity, fuelCapacity) {
        this.name = name;
        this.cargo = {
            capacity: cargoCapacity
        };
        this.fuelCapacity = fuelCapacity;
        Object.freeze(this);
    }
}

/**
 * ShipType enum
 *
 * @type frozen enum w/ fields corresponding to frozen ships
 */
const ShipType = Object.freeze({
    FLEA: new _ShipType("Flea", 5, 20),
    GNAT: new _ShipType("Gnat", 15, 15),
    FIREFLY: new _ShipType("Firefly", 20, 20)
});