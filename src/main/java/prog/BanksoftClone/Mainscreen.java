package prog.BanksoftClone;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;

public class Mainscreen extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnMustYonetim;
	private JMenu mnKartYonetim;
	private JMenu mnMustVeKart;
	private JMenuItem mntmMusteriYonetimGiris;
	private JMenuItem mntmMusteriYonetimGuncelleme;
	private JMenuItem mntmKYonetimGiris;
	private JMenuItem mntmKyonetimGuncelleme;
	private JMenuItem mntmKyonetimEticaretIzinDurumu;
	private JMenuItem mntmMteriNumarasndanBilgi;
	private JMenuItem mntmKartNumarasndanBilgi;
	private JMenuItem mntmMteriAdndanBilgi;
	private JMenuItem mntmCikis;
	private JMenuItem mntmHakkinda;
	private JMenuItem mntmKullaniciLogu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainscreen frame = new Mainscreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainscreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 387);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMustYonetim = new JMenu("Müşteri Yönetimi");
		menuBar.add(mnMustYonetim);
		
			mntmMusteriYonetimGiris = new JMenuItem("Giriş");
			mnMustYonetim.add(mntmMusteriYonetimGiris);
				mntmMusteriYonetimGiris.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						MusteriGiris mg = new MusteriGiris();
						mg.setVisible(true);
						
					}
				});
			
			mntmMusteriYonetimGuncelleme = new JMenuItem("Güncelleme");
			mnMustYonetim.add(mntmMusteriYonetimGuncelleme);
				mntmMusteriYonetimGuncelleme.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						MusteriGuncelle mgu = new MusteriGuncelle();
						mgu.setVisible(true);
						
					}
				});
			
		mnKartYonetim = new JMenu("Kart Yönetimi");
		menuBar.add(mnKartYonetim);
		
			mntmKYonetimGiris = new JMenuItem("Giriş");
			mnKartYonetim.add(mntmKYonetimGiris);
			mntmKYonetimGiris.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					KartGiris kvhgiris = new KartGiris();
					kvhgiris.setVisible(true);
					
				}
			});
			
			mntmKyonetimGuncelleme = new JMenuItem("Güncelleme");
			mnKartYonetim.add(mntmKyonetimGuncelleme);
			
			mntmKyonetimEticaretIzinDurumu = new JMenuItem("E-Ticaret İzin Durumu");
			mnKartYonetim.add(mntmKyonetimEticaretIzinDurumu);
		
		mnMustVeKart = new JMenu("Müşteri ve Kullanıcı Bilgileri İnceleme");
		menuBar.add(mnMustVeKart);
		
			mntmMteriNumarasndanBilgi = new JMenuItem("Müşteri Numarasından Bilgi Getirme");
			mnMustVeKart.add(mntmMteriNumarasndanBilgi);
			
			mntmKartNumarasndanBilgi = new JMenuItem("Kart Numarasından Bilgi Getirme");
			mnMustVeKart.add(mntmKartNumarasndanBilgi);
			
			mntmMteriAdndanBilgi = new JMenuItem("Müşteri Adından Bilgi Getirme");
			mnMustVeKart.add(mntmMteriAdndanBilgi);
		
		mntmKullaniciLogu = new JMenuItem("Kullanıcı Logu");
		menuBar.add(mntmKullaniciLogu);
		
		mntmHakkinda = new JMenuItem("Hakkında");
		menuBar.add(mntmHakkinda);
		
		mntmCikis = new JMenuItem("Çıkış");
		menuBar.add(mntmCikis);
		mntmCikis.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
