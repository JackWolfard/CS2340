class Ship {
    constructor(shipType) {
        this.name = shipType.name;
        this.shipType = shipType;
        this.fuel = shipType.fuelCapacity;
        this.cargo = {
            _shipType: this.shipType,
            get capacity() { return this._shipType.cargo.capacity; },
            inventory: new Map(),
            size: 0,
            add: item => {
                if (this.cargo.size < this.cargo.capacity) {
                    let curr = this.cargo.inventory.get(item);
                    curr = (curr === undefined) ? 0 : curr;
                    this.cargo.inventory.set(item, curr + 1);
                    this.cargo.size++;
                } else {
                    throw "Cargo full. Cannot add item.";
                }
            },
            remove: item => {
                if (this.cargo.inventory.get(item) !== undefined) {
                    let curr = this.cargo.inventory.get(item);
                    if (curr <= 1) {
                        this.cargo.invetory.delete(item);
                    } else {
                        this.cargo.inventory.set(item, curr - 1);
                    }
                    this.cargo.size--;
                } else {
                    throw "Cannot find item in cargo.";
                }
            }
        }
    }
}