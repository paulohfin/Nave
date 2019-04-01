import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Timer extends javax.swing.JLabel{
	JLabel txt;
	private int tempo;
	private boolean naveVive;
	
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public Timer(ImageIcon icon,int x, int y, int width, int heigth, int tempo){
		this.setIcon(icon);
		this.setBounds(x, y, width, heigth);
		this.tempo = tempo;
		this.setText(this.tempo + "");
		this.naveVive = true;
	}
	
	private boolean mudarTempo(){
		this.tempo = this.getTempo() - 1;
		txt.setText(this.tempo + "");
		if(this.tempo > 0 && this.naveVive)
			return true;
		else return false;
	}
	public boolean isNaveVive() {
		return naveVive;
	}
	public void setNaveVive(boolean naveVive) {
		this.naveVive = naveVive;
	}
	public void addTxt(){
		txt = new JLabel();
		txt.setForeground(Color.white);
		txt.setBounds(0,0,80,80);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		Font f = new Font("SansSerif", Font.BOLD, 20);
		txt.setFont(f);
		
		new Thread(){
			public void run(){
				while(mudarTempo()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						//
					}
				}
			}			
		}.start();
		this.add(txt);
	}
}
