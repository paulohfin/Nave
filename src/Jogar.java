
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

import sun.audio.*;
import java.util.logging.*;

public class Jogar {
	JFrame janela;
	JLabel tela;
	Fundo fundo;
	Timer timer;
	Ponto ponto;
	Nave nave;
	SangueNave sangueNave;
	boolean morreu;
	
	ArrayList<Inimigo> arrayMeteoro;
	ArrayList<Missel> arrayMisseis;
	boolean atirar, criarMeteoro;
	
	public void atritoNave(){
		nave.setSangue(nave.getSangue() - 1);
		sangueNave.setSangue(nave.getSangue());
		if(nave.getSangue() <= 0){
			/*
			 * Adicionar som de explosão da nave
			 *InputStream in;
	         *try {
	         *    in = new FileInputStream(url);
			 *
	         *    AudioStream as = new AudioStream(in);      
	         *    AudioPlayer.player.start(as);    
	         *} catch (IOException ex) {
	         *    //
	         *}
	        */
			nave.setVisible(false);
			morreu = true;
			timer.setNaveVive(false);
		}
	}
	
	public void explosaoMeteoro(Inimigo meteoro){		
		if(meteoro.getSangue() > 100)
			ponto.setPonto(100 + ponto.getPonto());
		else
			ponto.setPonto(meteoro.getSangue() + ponto.getPonto());	
		
		meteoro.setSangue(meteoro.getSangue() - 100);	
		
		/*
		 * Adicionar som de explosão do tiro nos meteoros
		 *InputStream in;
         *try {
         *    in = new FileInputStream(url);
		 *
         *    AudioStream as = new AudioStream(in);      
         *    AudioPlayer.player.start(as);    
         *} catch (IOException ex) {
         *    //
         *}
        */
	}
	/*
	 * Função responsável para criar o objeto de sangue da nave
	 */
	public void criarSangueNave(){
		sangueNave = new SangueNave(30, 25, 230, 30);
		tela.add(sangueNave);
	}
	/*
	 * Função responsável por redimensionar a imagem
	 * recebe a url da imagem e o tamanho a ser redimensionado
	 * retorna a imagem redimensionada
	 */
	public ImageIcon redimensionarImagem(String url, int size){
		ImageIcon icon = new ImageIcon(url);
		
		Image image = icon.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	/*
	 * Função responsável por criar o objeto Tiro
	 * adiciona do array de tiros e no JLabel
	 */
	public void atirando(){
		Missel missel = new Missel(0, -2, redimensionarImagem("src/fig/fire.png", 25), nave.getLocation().x + 14, nave.getLocation().y - 3, 25, 25);
		
		tela.add(missel);
		arrayMisseis.add(missel);
	}
	/*
	 * Função responsável por criar o objeto Timer
	 * adiciona o tempo no jogo (no JLabel) 
	 */
	public void timer(){
		timer = new Timer(redimensionarImagem("src/fig/tempo.png", 80), 310,5,80,80, 300);
		timer.addTxt();		
		
		tela.add(timer);
	}
	/*
	 * Função responsável por criar o objeto Ponto
	 * adiciona marcadores de pontos no jogo (no JLabel)
	 */
	public void gerarPontuacao(){
		ponto = new Ponto(500,5,150,50);
		
		tela.add(ponto);
	}
	/*
	 * função responsável para criar inimigos(meteoro)
	 * inicializa uma thread que cria o JLabel
	 */
	public void criandoMeteoro(){
		int n = (int)(Math.random() * 4);
		int size = (int)(Math.random() * 50 + 25);
		
		Inimigo inimigo = new Inimigo(Math.random() * 1000 - 500, Math.random() * 1000 + 250,
				redimensionarImagem("src/fig/meteoro" + (n + 1) +".png", size), (int)(Math.random() * fundo.getSize().getWidth()), -size, size, size);
						
		tela.add(inimigo);
		arrayMeteoro.add(inimigo);
	}
	/*
	 * função responsável por sinalizar para criar um inimigo
	 * dorme por um tempo aleatório
	 */
	public void gerarInimigo(){
		new Thread(){
			public void run(){
				while(true){
					criarMeteoro = true;
					try {
						Thread.sleep((int)(Math.random() * (timer.getTempo() * 25) + 1000));
					} catch (InterruptedException e) {
						//e.printStackTrace();
					}
				}
			}
		}.start();
	}
	/*
	 * funções de segundo plano
	 * cria, move e remove meteoro
	 * cria, move e remove missel
	 * verifica atrito entre meteoro e nave, meteoro e missel
	 */
	public void acaoSegundoPlano(){
		timer();
		gerarInimigo();
		gerarPontuacao();
		
		new Thread(){
			public void run(){
				int i;
				while(!morreu){
					//atirar
					if(atirar){
						atirando();
						atirar = false;
					}
					//criar meteoro
					if(criarMeteoro){
						criandoMeteoro();
						criarMeteoro = false;
					}
					//mover meteoro
					for(Inimigo inimigo: arrayMeteoro)
						inimigo.mexer();
					//apagar meteoros fora da tela
					for(i = 0; i < arrayMeteoro.size(); i++){
						Inimigo meteoro = arrayMeteoro.get(i);
						if(meteoro.getX() < -50 || meteoro.getX() > 750 || meteoro.getY() > 650){
							meteoro.setVisible(false);
							arrayMeteoro.remove(meteoro);
							i--;
						}
					}
					//mover missel
					for(Missel missel : arrayMisseis)
						missel.mexer();
					//apagar missel fora da tela
					for(i = 0; i < arrayMisseis.size(); i++){
						Missel missel = arrayMisseis.get(i);
						if(missel.getY() < -50){
							missel.setVisible(false);
							arrayMisseis.remove(missel);
							i--;
						}
					}
					//verificar contato entre missel e meteoro
					for( i = 0; i < arrayMisseis.size(); i++){
						for(int j = 0; j < arrayMeteoro.size(); j++){
							Missel missel = arrayMisseis.get(i);
							Inimigo meteoro = arrayMeteoro.get(j);
							if(missel.getX() + missel.getSize().getWidth() >= meteoro.getX()
									&& missel.getX() <= meteoro.getX() + meteoro.getSize().getWidth()
									&& missel.getY() + missel.getSize().getHeight() >= meteoro.getY()
											&& missel.getY() <= meteoro.getY() + meteoro.getSize().getHeight()){

								arrayMisseis.remove(missel);
								missel.setVisible(false);
								
								explosaoMeteoro(meteoro);
								if(meteoro.getSangue() <= 0){
									arrayMeteoro.remove(meteoro);								
									meteoro.setVisible(false);
								}
								break;
							}
						}
					}
					//verificar atrito entre meteoro e nave
					for(Inimigo meteoro:arrayMeteoro)
						if(nave.getX() + nave.getSize().getWidth() >= meteoro.getX()
								&& nave.getX() <= meteoro.getX() + meteoro.getSize().getWidth()
								&& nave.getY() + nave.getSize().getHeight() >= meteoro.getY()
										&& nave.getY() <= meteoro.getY() + meteoro.getSize().getHeight()){
							atritoNave();
						}
					//movendo o fundo da tela
					fundo.mexer();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						//
					}
				}
			}
		}.start();
	}
	/*
	 * função responsável para atribuir ação aos cliques do teclado
	 */
	public void acoesClicar(){
		
		janela.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				
				switch(evt.getKeyCode()){
					case 37:
						nave.mexer(-1,0);
						break;
					case 38:
						nave.mexer(0,-1);
						break;
					case 39:
						nave.mexer(1,0);
						break;
					case 40:
						nave.mexer(0,1);
						break;
					case 32:
						/*
						 * Adicionar som de tiro
						 *InputStream in;
				         *try {
				         *    in = new FileInputStream(url);
						 *
				         *    AudioStream as = new AudioStream(in);      
				         *    AudioPlayer.player.start(as);    
				         *} catch (IOException ex) {
				         *    //
				         *}
				        */
						atirar = true;
						break;
				}
			}
		});
	}
	/*
	 * construtor da classe
	 * responsável por inicializar todas as variáveis, e chamar as funções de ação do segundo plano
	 */
	public Jogar(){
		//inicializando variáveis
		janela = new JFrame();
		tela = new JLabel();
		arrayMeteoro = new ArrayList<Inimigo>();
		arrayMisseis = new ArrayList<Missel>();
		atirar = false;
		criarMeteoro = false;
		morreu=false;
		
		//criando a imagem de fundo
		fundo = new Fundo(0, 0, 700, 600, "src/fig/espaco.png");
		
		//criando a imagem da nave
		nave = new Nave(4, 4, redimensionarImagem("src/fig/nave.png", 50), 300, 450, 50, 50);
		criarSangueNave();
		
		tela.setBounds(0,0,700,600);
		
		//inserindo a nave e o fundo no JFrame
		tela.add(nave);
		janela.add(tela);
		janela.add(fundo);
		
		janela.setLayout(null);
		janela.setSize(700,600);
		janela.setVisible(true);
		
		//jogabilidades
		acoesClicar();
		
		//inicializando funções de segundo plano
		acaoSegundoPlano();		
	}
}
