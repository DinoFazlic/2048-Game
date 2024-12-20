package okvir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import projekat.Kontroler;
import projekat.Polje;
import projekat.desktop2048;

public class Prikaz2048 extends JFrame {
	
	private JLabel bodovi;
	private static final int sirina = 100;
	private static final int visina = 100;
	private static final int polje_sirina = sirina * Polje.velicina + 50;
	private static final int polje_visina = visina * Polje.velicina + 100;
	
	private static final Color boja0 = new Color(255,255,255);
	private static final Color boja2 = new Color(240,240,240);
	private static final Color boja4 = new Color(237,224,200);
	private static final Color boja8 = new Color(242,177,121);
	private static final Color boja16 = new Color(245,149,99);
	private static final Color boja32 = new Color(246,124,95);
	private static final Color boja64 = new Color(246,94,59);
	private static final Color boja128 = new Color(237,207,114);
	private static final Color boja256 = new Color(237,204,97);
	private static final Color boja512 = new Color(237,200,80);
	private static final Color boja1024 = new Color(237,197,63);
	private static final Color boja2048 = new Color(237,194,46);

	private class polje2048 extends JLabel{
		
		public polje2048() {
			super("", SwingConstants.CENTER);
			setOpaque(true);
			setPreferredSize(new Dimension(polje_sirina,polje_visina));
			setBorder(BorderFactory.createLineBorder(new Color(147,133,120),5));
			setBackground(boja0);
			setFont(new Font("Serif",Font.BOLD,40));
		}
		
		public void setBroj(int x) {
			if(x == 0)
				setText("");
			else
				setText("" + x);
			
			switch(x) {
			case 0:
				setBackground(boja0);
				break;
			case 2:
				setBackground(boja2);
				break;
			case 4:
				setBackground(boja4);
				break;
			case 8:
				setBackground(boja8);
				break;
			case 16:
				setBackground(boja16);
				break;
			case 32:
				setBackground(boja32);
				break;
			case 64:
				setBackground(boja64);
				break;
			case 128:
				setBackground(boja128);
				break;
			case 256:
				setBackground(boja256);
				break;
			case 512:
				setBackground(boja512);
				break;
			case 1024:
				setBackground(boja1024);
				break;
			case 2048:
				setBackground(boja2048);
				break;
			}
		}
	}
	
	private polje2048 polja[][];
	private Kontroler kontroler;
	
	/*
	public Prikaz2048() {
		paneli();
		setSize(new Dimension(polje_sirina,polje_visina));
		setTitle("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}
	*/
	public Prikaz2048() {
		paneli();
		setSize(new Dimension(polje_sirina, polje_visina));
		setTitle("2048");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		setResizable(false);
		setVisible(true);
		
		if (kontroler == null) {
	        kontroler = new Kontroler();
	    }

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int izbor = JOptionPane.showConfirmDialog(Prikaz2048.this,
						"Želite li sačuvati stanje igre prije izlaska?",
						"Spremanje igre", JOptionPane.YES_NO_OPTION);

				if (izbor == JOptionPane.YES_OPTION) {
					if (kontroler.sacuvajStanje("stanje.txt")) {
						System.out.println("Stanje igre je uspješno sačuvano.");
					} else {
						JOptionPane.showMessageDialog(Prikaz2048.this,
								"Greška pri spremanju stanja igre.",
								"Greška", JOptionPane.ERROR_MESSAGE);
					}
				}

				setVisible(false);
				dispose();
			}
		});

		
	}
	
	public void pokreniKontroler(Kontroler kontroler) {
		this.kontroler = kontroler;
	}
	
	private void paneli() {
		JPanel glavni = new JPanel(new BorderLayout());
		JPanel vrh = new JPanel(new FlowLayout());
		bodovi = new JLabel("Trenutni bodovi: ");
		vrh.add(bodovi);
		JPanel centar = new JPanel(new GridLayout(Polje.velicina,Polje.velicina));
		
		polja = new polje2048[Polje.velicina][Polje.velicina];
		
		for(int i = 0;i < Polje.velicina;i++) {
			for(int j = 0;j < Polje.velicina;j++) {
				polja[i][j] = new polje2048();
				centar.add(polja[i][j]);
			}
		}
		
		glavni.add(vrh,BorderLayout.NORTH);
		glavni.add(centar,BorderLayout.CENTER);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					kontroler.uradiPotez(desktop2048.potez_gore);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					kontroler.uradiPotez(desktop2048.potez_dole);
				}

				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					kontroler.uradiPotez(desktop2048.potez_lijevo);
				}

				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					kontroler.uradiPotez(desktop2048.potez_desno);
				}
			}
			
			
		});
		
		setContentPane(glavni);
	}
	
	public void promijeniPolje(Polje polje, int score) {
		int[][] temp = polje.getPolje();
		
		for(int i = 0;i < Polje.velicina;i++) {
			for(int j = 0;j < Polje.velicina;j++) {
				polja[i][j].setBroj(temp[i][j]);
			}
		}
		bodovi.setText("Trenutni score: " + score);
	}

	public void prikaziRezultat(String s) {
		JOptionPane.showMessageDialog(this,s, "IGRA JE GOTOVA !! ",JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();
		/*System.exit(0);*/
	}
	
	




	
}
