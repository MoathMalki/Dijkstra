package application;


public class Edge {
	Capital adjacent;
	double distance;
	public Edge(Capital adjacentCity, double distance) {
		this.adjacent = adjacentCity;
		this.distance = distance;
	}

	public Capital getAdjacentCity() {
		return adjacent;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "Edge [adjacent=" + adjacent + ", distance=" + distance + "]";
	}
	
}
