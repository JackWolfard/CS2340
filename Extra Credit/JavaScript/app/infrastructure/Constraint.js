class Constraint {
    constructor(raw_edges, raw_dimensions, center={horizontal: false, vertical: false},
                relatives={}) {
        this.raw_edges = raw_edges;
        this.raw_dimensions = raw_dimensions;
        this.center = center;
        this.relatives = relatives;
        this.update();
    }

    get dimensions() {
        return {
            width: Math.floor(this.raw_dimensions.width / 100 * width),
            height: Math.floor(this.raw_dimensions.height / 100 * height)
        };
    }

    get edges() {
        let edges = {
            top: Math.floor(this.raw_edges.top / 100 * height),
            bottom: Math.floor(this.raw_edges.bottom / 100 * height),
            left: Math.floor(this.raw_edges.left / 100 * width),
            right: Math.floor(this.raw_edges.right / 100 * width)
        };
        for (let property in edges) {
            if (isNaN(edges[property])) {
                edges[property] = undefined;
            }
        }
        return edges;
    }

    update() {
        let limits = {top: 0, bottom: height, left: 0, right: width};
        let map = {top: "bottom_edge", bottom: "top_edge", left: "right_edge", right: "left_edge"};

        for (let relative in this.relatives) {
            if (this.relatives[relative] !== undefined) {
                limits[relative] = this.relatives[relative][map[relative]];
            }
        }

        if (this.center.horizontal) {
            let halfway = Math.floor((limits.right - limits.left) / 2);
            let offset = Math.floor(this.dimensions.width / 2);
            this.left_edge = limits.left + halfway - offset;
            this.right_edge = limits.left + halfway + offset;
        } else {
            if (this.edges.left !== undefined) {
                this.left_edge = limits.left + this.edges.left;
                this.right_edge = this.left_edge + this.dimensions.width;
            } else {
                // assume right edge is passed in
                this.right_edge = limits.right - this.edges.right;
                this.left_edge = this.right_edge - this.dimensions.width;
            }
        }

        if (this.center.vertical) {
            let halfway = Math.floor((limits.bottom - limits.top) / 2);
            let offset = Math.floor(this.dimensions.height / 2);
            this.top_edge = limits.top + halfway - offset;
            this.bottom_edge = limits.top + halfway + offset;
        } else {
            if (this.edges.top !== undefined) {
                this.top_edge = limits.top + this.edges.top;
                this.bottom_edge = this.top_edge + this.dimensions.height;
            } else {
                // assume right edge is passed in
                this.bottom_edge = limits.bottom - this.edges.bottom;
                this.top_edge = this.bottom_edge - this.dimensions.height;
            }
        }

        this.x = this.left_edge;
        this.y = this.top_edge;
        this.width = this.dimensions.width;
        this.height = this.dimensions.height;
    }
}