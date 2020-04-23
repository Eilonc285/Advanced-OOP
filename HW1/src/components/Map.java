package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Map {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;

	public Map() {
		Random rand = new Random();
		Point p_temp = new Point(1, 2);
		for (int i = 0; i < 20; i++) {

			Point p = new Point(rand.nextInt(p_temp.getX_max()), rand.nextInt(p_temp.getY_max()));
			Junction j = new Junction(String.format("Number %d", i + 1), p);
			this.junctions.add(j);
		}

		for (int i = 19; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				Road r = new Road(this.junctions.get(i), this.junctions.get(j));
				this.roads.add(r);
			}
		}
	}

	public Map(int junctions, int roads) {
		Random rand = new Random();
		Point p_temp = new Point(1, 2);
		for (int i = 0; i < junctions; i++) {

			Point p = new Point(rand.nextInt(p_temp.getX_max()), rand.nextInt(p_temp.getY_max()));
			Junction j = new Junction(String.format("Number %d", i + 1), p);
			this.junctions.add(j);
		}

		for (int i = roads; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				Road r = new Road(this.junctions.get(i), this.junctions.get(j));
				this.roads.add(r);
			}
		}

	}

	public Map(ArrayList<Junction> juncs) {
		this.junctions = juncs;
		for (int i = 0; i < juncs.size(); i++) {
			for (int j = 0; j < juncs.size(); j++) {
				if (!juncs.get(j).equals(juncs.get(i))) {
					Road r = new Road(juncs.get(i), juncs.get(j));
					this.roads.add(r);
				}
			}
		}
	}

	public Map(ArrayList<Junction> juncs, ArrayList<Road> roads) {
		this.junctions = juncs;
		this.roads = roads;
	}

	public void addRoad(Road r) {
		this.roads.add(r);
	}

	public void removeRoad(Road r) {
		for (int i = 0; i < this.roads.size(); i++) {
			if (this.roads.get(i).equals(r)) {
				this.roads.remove(i);
				break;
			}
		}
	}

	public void addJunction(Junction junc) {
		this.junctions.add(junc);
	}

	public void removeJunction(Junction junc) {
		for (int i = 0; i < this.roads.size(); i++) {
			if (this.roads.get(i).getFromJunc().equals(junc) || this.roads.get(i).getToJunc().equals(junc)) {
				this.roads.remove(i);
			}
		}
		for (int i = 0; i < this.junctions.size(); i++) {
			if (this.junctions.get(i).equals(junc)) {
				this.junctions.remove(i);
				break;
			}
		}
	}
}
