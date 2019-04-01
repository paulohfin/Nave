import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fundo extends javax.swing.JLabel{
	JLabel fundo;
	int horizontal, vertical;
	double px, py;
	double dx, dy;
	
	public Fundo(int x, int y, int horizontal, int vertical, String url){
		this.horizontal=horizontal;
		this.vertical=vertical;
		fundo = new JLabel(new ImageIcon(url));
		fundo.setBounds(horizontal-1220, vertical-3040, 1220, 3040);
		this.add(fundo);
		this.setBounds(x, y, horizontal, vertical);
		this.px = fundo.getX();
		this.py = fundo.getY();
		this.dx = 0;
		this.dy = 0.25;
	}
	public void mexer(){
		this.px+=dx;
		this.py+=dy;
		fundo.setBounds((int)this.px, (int)this.py, 1220,3040);
	}
}
