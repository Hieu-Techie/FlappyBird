package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ChimneyGroup {

	private QueueList<Chimney> chimneys;

	private BufferedImage chimneyImage, chimneyImage2;

	public static int SIZE = 6;

	private int topChimneyY = -350;
	private int bottomChimneyY = 200;

	public ChimneyGroup() {

		try {

			chimneyImage = ImageIO.read(new File("src/Sprites/chimney.png"));
			chimneyImage2 = ImageIO.read(new File("src/Sprites/chimney_.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		chimneys = new QueueList<Chimney>();

		Chimney cn;

		int deltaY = getRandomY();

		for (int i = 0; i < SIZE / 2; i++) {

			cn = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74, 400);
			chimneys.push(cn);

			cn = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
			chimneys.push(cn);
		}
	}

	public Chimney getChimney(int i) {
		return chimneys.get(i);
	}

	public int getRandomY() {
		Random random = new Random();
		int a;
		a = random.nextInt(10);

		return a * 28;
	}

	public void resetChimneys() {
		chimneys = new QueueList<Chimney>();

		Chimney cn;

		for (int i = 0; i < SIZE / 2; i++) {

			int deltaY = getRandomY();

			cn = new Chimney(830 + i * 300, bottomChimneyY + deltaY, 74, 400);
			chimneys.push(cn);

			cn = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
			chimneys.push(cn);
		}
	}

	public void update() {
		for (int i = 0; i < SIZE; i++) {
			chimneys.get(i).update();
		}

		if (chimneys.get(0).getPosX() < -74) {

			int deltaY = getRandomY();

			Chimney cn;
			cn = chimneys.pop();
			cn.setPosX(chimneys.get(4).getPosX() + 300);
			cn.setPosY(bottomChimneyY + deltaY);
			cn.setIsBehindBird(false);
			chimneys.push(cn);

			cn = chimneys.pop();
			cn.setPosX(chimneys.get(4).getPosX());
			cn.setPosY(topChimneyY + deltaY);
			cn.setIsBehindBird(false);
			chimneys.push(cn);
		}
	}

	public void paint(Graphics2D g2) {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0)
				g2.drawImage(chimneyImage, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
			else
				g2.drawImage(chimneyImage2, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
		}
	}
}