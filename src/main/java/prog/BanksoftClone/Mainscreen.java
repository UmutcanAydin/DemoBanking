package prog.BanksoftClone;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale.Category;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Desktop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Umutcan Aydin
 * @since  2019-07-23
 */

@SuppressWarnings("serial")
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
	private JMenuItem mntmKartNumarasndanBilgi;
	private JMenuItem mntmCikis;
	private JMenuItem mntmHakkinda;
	private JMenuItem mntmKullaniciLogu;
	private static final Logger logger = LogManager.getLogger(Mainscreen.class);
	
	/**
	 * Aplikasyonu Çalıştırır.
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
	 * Aplikasyon ana ekranı.
	 * 
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
						logger.info("'Müşteri Giriş' sayfasına girildi.");
						MusteriGiris mg = new MusteriGiris();
						mg.setVisible(true);
					}
				});
			
			mntmMusteriYonetimGuncelleme = new JMenuItem("Güncelleme");
			mnMustYonetim.add(mntmMusteriYonetimGuncelleme);
				mntmMusteriYonetimGuncelleme.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						logger.info("'Müşteri Güncelleme' sayfasına girildi.");
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
					logger.info("'Kart-Hesap Giriş' sayfasına girildi.");
					KartGiris kvhgiris = new KartGiris();
					kvhgiris.setVisible(true);
					
				}
			});
			
			mntmKyonetimGuncelleme = new JMenuItem("Güncelleme");
			mnKartYonetim.add(mntmKyonetimGuncelleme);
			mntmKyonetimGuncelleme.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					logger.info("'Kart-Hesap Güncelleme' sayfasına girildi.");
					KartGuncelleme kgun = new KartGuncelleme();
					kgun.setVisible(true);
				}
			});
			
			mntmKyonetimEticaretIzinDurumu = new JMenuItem("E-Ticaret İzin Durumu");
			mnKartYonetim.add(mntmKyonetimEticaretIzinDurumu);
			mntmKyonetimEticaretIzinDurumu.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					logger.info("'E-Ticaret' sayfasına girildi.");
					Eticaret et = new Eticaret();
					et.setVisible(true);					
				}
			});
		
		mnMustVeKart = new JMenu("Müşteri ve Kullanıcı Bilgileri İnceleme");
		menuBar.add(mnMustVeKart);
			
			mntmKartNumarasndanBilgi = new JMenuItem("Kart Numarasından Bilgi Getirme");
			mnMustVeKart.add(mntmKartNumarasndanBilgi);
			mntmKartNumarasndanBilgi.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					logger.info("'Karttan Bilgi İnceleme' sayfasına girildi.");
					KarttanBilgiIncele kbi = new KarttanBilgiIncele();
					kbi.setVisible(true);
					
				}
			});
		
		mntmKullaniciLogu = new JMenuItem("Kullanıcı Logu");
		menuBar.add(mntmKullaniciLogu);
		mntmKullaniciLogu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Log lg = new Log();
				lg.setVisible(true);
				
			}
		});
		
		mntmHakkinda = new JMenuItem("Hakkında");
		menuBar.add(mntmHakkinda);
		mntmHakkinda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logger.info("'Hakkında' sayfasına girildi.");
				Hakkinda hk = new Hakkinda();
				hk.setVisible(true);
			}
		});
		
		mntmCikis = new JMenuItem("Çıkış");
		menuBar.add(mntmCikis);
		mntmCikis.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logger.info("Çıkış yapıldı.");
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
