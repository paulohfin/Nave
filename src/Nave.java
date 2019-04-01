import javax.swing.ImageIcon;

public class Nave extends javax.swing.JLabel{
	int sangue;
	public int getSangue() {
		return sangue;
	}

	public void setSangue(int sangue) {
		this.sangue = sangue;
	}
	int dx, dy;

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void mexer(int x, int y){
		if(this.getX() + this.dx * x > 0 && this.getX() + this.dx * x < 650 && this.getY() + this.dy * y > 50 && this.getY() + this.dy * y < 505)
			this.setLocation(this.getX() + x * this.dx, this.getY() + y * this.dy); 
	}
	public Nave(int dx, int dy, ImageIcon icon, int x, int y, int width, int heigth) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.setIcon(icon);
		this.setBounds(x, y, width, heigth);
		this.sangue=100;
	}
	
}
