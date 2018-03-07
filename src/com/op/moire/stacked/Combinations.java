package com.op.moire.stacked;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Combinations {

	private Graphics2D opG1;
	private Graphics2D opG2;
	private Graphics2D opG3;

	private int sbr = 9;
	private int swr = 6;
	private int hbr = 12;
	private int hwr = 9;
	private int h2br = 15;
	private int h2wr = 12;

	private Color col3 = new Color(128, 128, 128);
	private Color col2 = new Color(64, 64, 64);
	private Color col1 = new Color(0, 0, 0);
	private int startAng;

	public Combinations(Graphics2D opG1, Graphics2D opG2, Graphics2D opG3) {
		this.opG1 = opG1;
		this.opG2 = opG2;
		this.opG3 = opG3;
	}

	public void fillAll(int x, int y, boolean s1b, boolean s2b, boolean s3b,
			boolean h1b, boolean h2b) {
		startAng = (int) (360.0 * Math.random());
		if (h1b) {
			if (s1b && s2b) {
				draw(col1, col2, opG1, x, y, 0, sbr);
				draw(col1, col2, opG2, x, y, hbr - sbr, hbr);
			} else if (!s1b && s2b) {
				draw(col1, col2, opG1, x, y, 0, swr);
				draw(col1, col2, opG2, x, y, hbr - sbr, hbr);
			} else if (s1b && !s2b) {
				draw(col1, col2, opG1, x, y, 0, sbr);
				draw(col1, col2, opG2, x, y, hbr - swr, hbr);
			} else if (!s1b && !s2b) {
				draw(col1, col2, opG1, x, y, 0, swr);
				draw(col1, col2, opG2, x, y, hbr - swr, hbr);
			}

			if (h2b) {
				if (s3b) {
					draw(col1, col2, opG3, x, y, h2br - sbr, h2br);
				} else {
					draw(col1, col2, opG3, x, y, h2br - swr, h2br);
				}
			} else {
				if (s3b) {
					draw(col1, col2, opG3, x, y, h2wr - sbr, h2wr);
				} else {
					draw(col1, col2, opG3, x, y, h2wr - swr, h2wr);
				}
			}
		} else {
			if (s1b && s2b) {
				draw(col1, col2, opG1, x, y, 0, sbr);
				draw(col1, col2, opG2, x, y, hwr - sbr, hwr);
			} else if (!s1b && s2b) {
				draw(col1, col2, opG1, x, y, 0, swr);
				draw(col1, col2, opG2, x, y, hwr - sbr, hwr);
			} else if (s1b && !s2b) {
				draw(col1, col2, opG1, x, y, 0, sbr);
				draw(col1, col2, opG2, x, y, hwr - swr, hwr);
			} else if (!s1b && !s2b) {
				draw(col1, col2, opG1, x, y, 0, swr);
				draw(col1, col2, opG2, x, y, hwr - swr, hwr);
			}

			if (h2b) {
				if (s3b) {
					draw(col1, col2, opG3, x, y, h2br - sbr, h2br);
				} else {
					draw(col1, col2, opG3, x, y, h2br - swr, h2br);
				}
			} else {
				if (s3b) {
					draw(col1, col2, opG3, x, y, h2wr - sbr, h2wr);
				} else {
					draw(col1, col2, opG3, x, y, h2wr - swr, h2wr);
				}
			}
		}

	}

	private void draw(Color col, Color col2, Graphics2D opG, int x, int y,
			double r1i, double r1o) {
		opG.setColor(col);

		startAng = 0;
		float radius = 15;
		float[] dist = { .0f, 1.0f };
		int arcI = (int) (360.0 * (r1i / 15.0));
		int arcO = (int) (360.0 * (r1o / 15.0));
		int startAngle = startAng + arcI;
		int extent = arcO - arcI;

		// RadialGradientPaint p = new RadialGradientPaint(center, radius, dist,
		// colors);
		//
		// Code for a simple cone like from black over white to black
		Ellipse2D cone = new Ellipse2D.Double(x - radius, y - radius,
				radius * 2, radius * 2);
		Point2D center = new Point2D.Double(x, y);
		float[] fractions = { 1.0f, 0.0f };
		Color[] colors = { Color.WHITE, Color.BLACK };
		ConicalGradientPaint grad = new ConicalGradientPaint(center, fractions,
				colors);
		opG.setPaint(grad);
		opG.fill(cone);

		// opG.fill(new Arc2D.Float(x, y, radius * 2, radius * 2, 0, 270,
		// Arc2D.PIE));

		System.out.println("x:" + x + ", y:" + y);
		// opG.fill(new Arc2D.Float(x, y, radius * 2, radius * 2, 0, 270,
		// Arc2D.PIE));
		// opG.fillArc(x - 16, y - 16, 2 * 16, 2 * 16, startAng + arcI, arcO
		// - arcI);
		// opG.setColor(col);
		// opG.fillArc(x - 16, y - 16, 2 * 16, 2 * 16, startAng + arcI
		// + (arcI / 2), arcO - (arcI / 2));
		// opG.fillOval(x - r1i, y - r1i, 2 * r1i, 2 * r1i);

	}

	private void drawOLD(Color col, Graphics2D opG, int x, int y, int r1i,
			int r1o) {
		opG.setColor(col);
		opG.fillOval(x - r1o, y - r1o, 2 * r1o, 2 * r1o);
		opG.setColor(Color.WHITE);
		opG.fillOval(x - r1i, y - r1i, 2 * r1i, 2 * r1i);

	}
}
