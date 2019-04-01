import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ponto  extends javax.swing.JLabel{
	int ponto;

	public Ponto(int x, int y, int width, int height){
		this.setForeground(Color.yellow);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		Font f = new Font("SansSerif", Font.BOLD, 20);
		this.setFont(f);
		this.setText("0");
		
		this.setBounds(x, y, width, height);
		this.ponto = 0;
	}
	public void setPonto(int pto){
		this.ponto = pto;
		this.setText(this.ponto + "");
	}
	public int getPonto(){
		return this.ponto;
	}
}
