import javax.swing.ImageIcon;

public class Inimigo extends javax.swing.JLabel{
	double dx, dy, px, py;
	int sangue;

	public int getSangue() {
		return sangue;
	}

	public void setSangue(int sangue) {
		this.sangue = sangue;
	}

	public double getPx() {
		return px;
	}

	public void setPx(double px) {
		this.px = px;
	}

	public double getPy() {
		return py;
	}

	public void setPy(double py) {
		this.py = py;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void mexer(){
		this.px += dx/1000;
		this.py += dy/1000;
		this.setLocation((int)this.px, (int)this.py);
	}
	
	public Inimigo(double dx, double dy, ImageIcon icon, int x, int y, int width, int height) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.px = x;
		this.py = y;
		this.setIcon(icon);
		this.setBounds(x, y, width, height);
		this.sangue = width * height / 10;
	}
	
}
