import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class SangueNave extends javax.swing.JLabel{
	JLabel sangueNave;
	int x, y, horizontal, vertical;
	
	public SangueNave(int x, int y, int horizontal, int vertical){
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.vertical = vertical;
		
		this.sangueNave = new JLabel();
		this.sangueNave.setOpaque(true);
		this.sangueNave.setBackground(Color.red);
		this.sangueNave.setBounds(3, 3, horizontal - 6, vertical - 6);
		
		this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		this.setBounds(x, y, horizontal, vertical);
		this.add(this.sangueNave);
	}
	public void setSangue(int n){
		if(n > 0)
			sangueNave.setBounds(3, 3, (int)(n * horizontal / 100) - 6, this.vertical - 6);
		else sangueNave.setVisible(false);
	}
}