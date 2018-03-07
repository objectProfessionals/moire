package com.op.moire;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.op.paint.services.RendererUtils;
import com.op.paint.services.SharedUtils;

public class CardPrint {

	private static final String ipFileName1 = "face1a";
	private static final String ipFileName2 = "face2a";
	private static final String opFileName = "card1";
	private static final String dir = "misc/cards/";
	private static final String outFileExt = ".png";

	private double dpi = 100;
	private double mm2in = 25.4;
	private double wmm = 10.16;
	private double hmm = 10.16;
	private int w = (int) (dpi * (wmm / mm2in));
	private int h = (int) (dpi * (hmm / mm2in));
	private boolean addBorder = false;
	private double bordermm = 20;
	private double border = (dpi * (bordermm / mm2in));
	private int numCols = 4;
	private int wCol = w / numCols;

	private BufferedImage opImage;
	private Graphics2D opG;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CardPrint cp = new CardPrint();
		cp.setupWholeImage();
		cp.draw();
		cp.saveImage();
	}

	private void draw() throws IOException {
		String src = hostDir + dir;
		BufferedImage bi1 = ImageIO.read(new File(src + ipFileName1
				+ outFileExt));
		BufferedImage bi2 = ImageIO.read(new File(src + ipFileName2
				+ outFileExt));
		ArrayList<Color> cols1 = new ArrayList<Color>();
		cols1.add(Color.BLACK);
		cols1.add(Color.WHITE);
		HashMap<Color, Color> mapRev = new HashMap<Color, Color>();
		mapRev.put(Color.BLACK, Color.WHITE);
		mapRev.put(Color.WHITE, Color.BLACK);

		ArrayList<Color> cols = new ArrayList<Color>();
		for (int x = 0; x < wCol; x = x + 1) {
			for (int y = 0; y < h; y++) {
				int rnd = (int) (Math.random() * cols1.size());
				Color col = cols1.get(rnd);
				cols.add(col);
			}
		}
		int cOff = h * wCol;
		int wOff = wCol;
		ArrayList<Color> cols2 = new ArrayList<Color>();
		int i = 0;
		for (int x = 0; x < wCol; x = x + 1) {
			for (int y = 0; y < h; y++) {
				boolean in1 = bi1.getRGB(x, y) != 0;
				boolean in2 = bi2.getRGB(x, y) != 0;
				Color col = cols.get(i);
				opG.setColor(col);
				opG.fillRect(x, y, 1, 1);
				if (in1) {
					col = mapRev.get(col);
					opG.setColor(col);
					opG.fillRect(x + w, y, 1, 1);
				}
				cols2.add(col);
				i++;
			}
		}
	}

	public Color blend(Color c0, Color c1) {
		double totalAlpha = c0.getAlpha() + c1.getAlpha();
		double weight0 = c0.getAlpha() / totalAlpha;
		double weight1 = c1.getAlpha() / totalAlpha;

		double r = weight0 * c0.getRed() + weight1 * c1.getRed();
		double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
		double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
		double a = Math.max(c0.getAlpha(), c1.getAlpha());

		return new Color((int) r, (int) g, (int) b, (int) a);
	}

	protected void setupWholeImage() throws IOException {
		System.out.println("Creating...");
		opImage = createBufferedImage(wCol + w * 2, h);
		opG = (Graphics2D) opImage.getGraphics();
		opG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		opG.setColor(Color.WHITE);
		opG.fillRect(0, 0, wCol + w * 2, h);

	}

	protected void saveImage() throws Exception {
		BufferedImage opImage3 = opImage;
		if (addBorder) {
			int ww = (int) (((double) w * 2) + border);
			int hh = (int) (((double) h) + border);
			int wd = (int) (border);
			int hd = (int) (border);
			BufferedImage opImage2 = createBufferedImage(ww, hh);
			Graphics2D opG2 = (Graphics2D) opImage2.getGraphics();
			opG2.setColor(Color.WHITE);
			opG2.fillRect(0, 0, ww, hh);
			opG2.drawImage(opImage, null, wd, hd);
			opImage3 = opImage2;
		}
		System.out.println("Saving...");
		String src = hostDir + dir;
		File fFile1 = new File(src + opFileName + outFileExt);
		if (outFileExt.equals(".png")) {
			savePNGFile(opImage3, fFile1, dpi);
		} else {
			saveJPGFile(opImage3, fFile1, dpi);
		}
		opG.dispose();
		printFileInfo(fFile1);
	}

	private void printFileInfo(File fFile1) {
		Date now = new Date();
		System.out.println("Saved " + fFile1.getPath() + " @" + now);
	}
}
