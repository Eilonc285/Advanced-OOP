package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import components.Junction;
import components.LightedJunction;
import components.Road;
import components.Vehicle;
import utilities.GameDriver;

public class MyCanvas extends Canvas {

	private Color carColor;
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<Vehicle> vehicles;

	public MyCanvas() {
		Dimension dim = getPreferredSize();
		dim.setSize(800, 600);
		setPreferredSize(dim);
		junctions = GameDriver.getDriving().getMap().getJunctions();
		roads = GameDriver.getDriving().getMap().getRoads();
		vehicles = GameDriver.getDriving().getVehicles();
	}

	public void paint(Graphics g) {
		for (int i = 0; i < junctions.size(); i++) {
			paintJunction(g, junctions.get(i));
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i < roads.size(); i++) {
			g.drawLine((int) roads.get(i).getStartJunction().getX(), (int) roads.get(i).getStartJunction().getY(),
					(int) roads.get(i).getEndJunction().getX(), (int) roads.get(i).getEndJunction().getY());
		}
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vic = vehicles.get(i);
			if (vic.getCurrentRoutePart() instanceof Road) {
				double[] pos = ((Road) vic.getCurrentRoutePart()).getVehicleLocation(vic);
				drawRotetedVehicle(g, (int) pos[0], (int) pos[1],
						(int) ((Road) vic.getLastRoad()).getEndJunction().getX(),
						(int) ((Road) vic.getLastRoad()).getEndJunction().getY(), 10, 4);
			}
		}
	}

	public void setCarColor(Color c) {
		this.carColor = c;
	}

	private void drawRotetedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1, delta = 10;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = delta, xn = xm, ym = h, yn = -h, x;
		double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
		double sin = dy / D, cos = dx / D;
		x = xm * cos - ym * sin + x1;
		xx = xm1 * cos - ym1 * sin + x1;
		ym = xm * sin + ym * cos + y1;
		ym1 = xm1 * sin + ym1 * cos + y1;
		xm = x;
		xm1 = xx;
		x = xn * cos - yn * sin + x1;
		xx = xn1 * cos - yn1 * sin + x1;
		yn = xn * sin + yn * cos + y1;
		yn1 = xn1 * sin + yn1 * cos + y1;
		xn = x;
		xn1 = xx;
		int[] xpoints = { (int) xm1, (int) xn1, (int) xn, (int) xm };
		int[] ypoints = { (int) ym1, (int) yn1, (int) yn, (int) ym };
		g.fillPolygon(xpoints, ypoints, 4);
		g.setColor(Color.BLACK);
		g.fillOval((int) xm1 - 2, (int) ym1 - 2, 4, 4);
		g.fillOval((int) xn1 - 2, (int) yn1 - 2, 4, 4);
		g.fillOval((int) xm - 2, (int) ym - 2, 4, 4);
		g.fillOval((int) xn - 2, (int) yn - 2, 4, 4);

	}

	private void paintJunction(Graphics g, Junction j) {
		if (j instanceof LightedJunction) {
			if (((LightedJunction) j).getLights().getTrafficLightsOn()) {
				int roadIndex = ((LightedJunction) j).getLights().getGreenLightIndex();
				Junction endJunction = j.getExitingRoads().get(roadIndex).getEndJunction();
				drawRotetedTriangle(g, j.getX(), j.getY(), endJunction.getX(), endJunction.getY(), 25, 8);
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GREEN);
			}
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillOval((int) Math.round(j.getX()) - 10, (int) Math.round(j.getY()) - 10, 20, 20);
	}

	private void drawRotetedTriangle(Graphics g, double x1, double y1, double x2, double y2, double d, double h) {
		double m1 = (y2 - y1) / (x2 - x1);
		double beta = Math.atan(m1);
		double alpha = Math.atan((h / 2) / d);
		double theta1 = beta + alpha;
		double theta2 = beta - alpha;
		double l = Math.sqrt(Math.pow(d, 2) + Math.pow(h / 2, 2));
		double leftY = Math.sin(theta1) * l;
		double leftX = Math.cos(theta1) * l;
		double rightY = Math.sin(theta2) * l;
		double rightX = Math.cos(theta2) * l;

		int xSign = 1;
		int ySign = 1;

		if (x2 > x1) {
			xSign = 1;
			ySign = 1;
		} else {
			xSign = -1;
			ySign = -1;
		}

		int[] x = { (int) x1, (int) (x1 + (xSign * leftX)), (int) (x1 + (xSign * rightX)) };
		int[] y = { (int) y1, (int) (y1 + (ySign * leftY)), (int) (y1 + (ySign * rightY)) };

		Polygon p = new Polygon(x, y, 3);

		g.setColor(Color.GREEN);
		g.fillPolygon(p);

	}

}
