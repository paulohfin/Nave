import javax.swing.ImageIcon;

public class Missel extends javax.swing.JLabel{
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

	public void mexer(){
		this.setLocation(this.getX() + this.dx, this.getY() + this.dy);
	}
	public Missel(int dx, int dy, ImageIcon icon, int x, int y, int width, int height){
		this.dx = dx;
		this.dy = dy;
		this.setIcon(icon);
		this.setBounds(x, y, width, height);
		
	}
}
