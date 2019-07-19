package prog.BanksoftClone;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KartGiris extends JDialog {
	private JTextField txtMustGetir;
	private JTextField txtSoyad;
	private JTextField txtAd;
	private JTextField txtMustNo;
	private JTextField txtBabaAdi;
	private JTextField txtDogumYeri;
	private JTextField txtAnaHesap;
	private JTextField txtKartNo;
	private JTextField txtKartCvv;
	private String isim;
	private String soyisim;
	private String musterino;
	private String babaadi;
	private String dogumyeri;
	private String dogumtarihi;
	private String subekodu;
	private JFormattedTextField ftxtDogumTarihi;
	private JFormattedTextField ftxtKartGecerlilikTarihi;
	private JButton btnHesaplar;
	private JComboBox cbKartGrubu;
	private JComboBox<Object> cbSube;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KartGiris dialog = new KartGiris();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void bul() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Müşteriler");
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("musterino", txtMustGetir.getText());
		
		FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
		for (Document d : iterable) {
			isim = (String) d.get("isim");
			soyisim = (String) d.get("soyisim");
			babaadi = (String) d.get("babaadi");
			dogumyeri = (String) d.get("dogumyeri");
			dogumtarihi = (String) d.get("dogumtarihi");
			musterino = (String) d.get("musterino");
			subekodu = (String) d.get("subekodu");
		}
		txtAd.setText(isim);
		txtSoyad.setText(soyisim);
		txtMustNo.setText(musterino);
		txtBabaAdi.setText(babaadi);
		txtDogumYeri.setText(dogumyeri);
		ftxtDogumTarihi.setText(dogumtarihi);
		txtAnaHesap.setEnabled(true);
		btnHesaplar.setEnabled(true);
		cbKartGrubu.setEnabled(true);
		cbSube.setEnabled(true);
		cbSube.setSelectedItem(subekodu);
		txtKartNo.setEnabled(true);
		ftxtKartGecerlilikTarihi.setEnabled(true);
		txtKartCvv.setEnabled(true);
	}
	
	private void temizle() {
		txtAd.setText("");
		txtSoyad.setText("");
		txtMustNo.setText("");
		txtBabaAdi.setText("");
		txtDogumYeri.setText("");
		ftxtDogumTarihi.setText("");
		txtAnaHesap.setEnabled(false);
		btnHesaplar.setEnabled(false);
		cbKartGrubu.setEnabled(false);
		cbKartGrubu.setSelectedIndex(0);
		cbSube.setEnabled(false);
		cbSube.setSelectedIndex(0);
		cbSube.setSelectedItem("");
		txtKartNo.setEnabled(false);
		ftxtKartGecerlilikTarihi.setEnabled(false);
		txtKartCvv.setEnabled(false);
	}

	/**
	 * Create the dialog.
	 */
	public KartGiris() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 626);
		getContentPane().setLayout(new BorderLayout());
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			
			
				JPanel pnlKartGiris = new JPanel();
				tabbedPane.addTab("Kart Girişi", null, pnlKartGiris, null);
				pnlKartGiris.setLayout(new BorderLayout(0, 0));
				
				JPanel pnlHesapGiriş = new JPanel();
				tabbedPane.addTab("Hesap Girişi", null, pnlHesapGiriş, null);
				pnlHesapGiriş.setLayout(new BorderLayout(0, 0));
				
				JPanel panel = new JPanel();
				pnlKartGiris.add(panel, BorderLayout.NORTH);
				
				txtMustGetir = new JTextField();
				txtMustGetir.setColumns(10);
				
				JButton btnMustBul = new JButton("Bul");
				btnMustBul.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bul();
					}
				});
				
				JLabel lblMteriNo = new JLabel("Müşteri No:");
				lblMteriNo.setLabelFor(txtMustGetir);
				lblMteriNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				txtSoyad = new JTextField();
				txtSoyad.setEditable(false);
				txtSoyad.setColumns(10);
				
				txtAd = new JTextField();
				txtAd.setEditable(false);
				txtAd.setColumns(10);
				
				txtMustNo = new JTextField();
				txtMustNo.setEditable(false);
				txtMustNo.setColumns(10);
				
				txtBabaAdi = new JTextField();
				txtBabaAdi.setEditable(false);
				txtBabaAdi.setColumns(10);
				
				txtDogumYeri = new JTextField();
				txtDogumYeri.setEditable(false);
				txtDogumYeri.setColumns(10);
				
				JLabel lblAd = new JLabel("Ad:");
				lblAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblSoyad = new JLabel("Soyad:");
				lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblMusteriNo = new JLabel("Müşteri No:");
				lblMusteriNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblBabaAd = new JLabel("Baba Adı:");
				lblBabaAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblDogumYeri = new JLabel("Doğum Yeri:");
				lblDogumYeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblDogumTarihi = new JLabel("Doğum Tarihi:");
				lblDogumTarihi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				ftxtDogumTarihi = new JFormattedTextField();
				ftxtDogumTarihi.setEditable(false);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAd)
										.addComponent(lblBabaAd))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtAd, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtBabaAdi, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
									.addGap(35)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDogumYeri, Alignment.TRAILING)
										.addComponent(lblSoyad, Alignment.TRAILING))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSoyad, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDogumYeri, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMteriNo)
									.addGap(4)
									.addComponent(txtMustGetir, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
							.addGap(35)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(28)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblMusteriNo)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtMustNo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblDogumTarihi)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(ftxtDogumTarihi, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnMustBul)))
							.addGap(168))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblMteriNo)
										.addComponent(txtMustGetir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnMustBul))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtSoyad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtMustNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblSoyad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblMusteriNo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtAd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAd, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(txtDogumYeri, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(20)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtBabaAdi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDogumYeri)
											.addComponent(lblBabaAd, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblDogumTarihi, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
											.addComponent(ftxtDogumTarihi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
							.addContainerGap())
				);
				panel.setLayout(gl_panel);
				
				JPanel panel_1 = new JPanel();
				panel_1.setToolTipText("");
				pnlKartGiris.add(panel_1, BorderLayout.SOUTH);
				
				JButton btnGiris = new JButton("Giriş");
				panel_1.add(btnGiris);
				
				JButton btnTemizle = new JButton("Temizle");
				btnTemizle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						temizle();
					}
				});
				panel_1.add(btnTemizle);
				
				JButton btnYeniMusteri = new JButton("Yeni Müşteri");
				panel_1.add(btnYeniMusteri);
				
				JButton btnExel = new JButton("Exel'e Aktar");
				panel_1.add(btnExel);
				
				JButton btnCikis = new JButton("Çıkış");
				btnCikis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				panel_1.add(btnCikis);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
				pnlKartGiris.add(panel_2, BorderLayout.CENTER);
				
				JLabel lblKartAnaHesap = new JLabel("Kart Ana Hesabı:");
				lblKartAnaHesap.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				txtAnaHesap = new JTextField();
				txtAnaHesap.setEnabled(false);
				txtAnaHesap.setEditable(false);
				txtAnaHesap.setColumns(10);
				
				btnHesaplar = new JButton("Hesaplar");
				btnHesaplar.setEnabled(false);
				
				JLabel lblKartGrubu = new JLabel("Kart Grubu:");
				lblKartGrubu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				cbKartGrubu = new JComboBox();
				cbKartGrubu.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cbKartGrubu.setEnabled(false);
				cbKartGrubu.setModel(new DefaultComboBoxModel(new String[] {"Troy", "Platin", "Visa Electron", "Mastercard maestro"}));
				
				cbSube = new JComboBox<Object>();
				cbSube.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cbSube.setEnabled(false);
				cbSube.setModel(new DefaultComboBoxModel(new String[] {"Sube1", "Sube2", "Sube3", "Sube4"}));
				
				JLabel lblSubeKodu = new JLabel("Şube Kodu:");
				lblSubeKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblKartNo = new JLabel("Kart No:");
				lblKartNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				txtKartNo = new JTextField();
				txtKartNo.setEnabled(false);
				txtKartNo.setColumns(10);
				
				JLabel lblKartGecerlilikTarihi = new JLabel("Kart Geçerlilik Tarihi:");
				lblKartGecerlilikTarihi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				JLabel lblKartCvv = new JLabel("Kart CVV:");
				lblKartCvv.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				ftxtKartGecerlilikTarihi = new JFormattedTextField();
				ftxtKartGecerlilikTarihi.setEnabled(false);
				
				txtKartCvv = new JTextField();
				txtKartCvv.setEnabled(false);
				txtKartCvv.setColumns(10);
				GroupLayout gl_panel_2 = new GroupLayout(panel_2);
				gl_panel_2.setHorizontalGroup(
					gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap(119, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addComponent(lblKartGrubu, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbKartGrubu, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_panel_2.createSequentialGroup()
											.addComponent(lblKartNo, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
											.addGap(27)
											.addComponent(txtKartNo, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
									.addGap(125)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblKartGecerlilikTarihi)
										.addComponent(lblSubeKodu, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cbSube, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
										.addComponent(ftxtKartGecerlilikTarihi))
									.addGap(131))
								.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
									.addComponent(lblKartAnaHesap)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAnaHesap, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnHesaplar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(287))
								.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
									.addComponent(lblKartCvv, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtKartCvv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(417))))
				);
				gl_panel_2.setVerticalGroup(
					gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKartAnaHesap)
								.addComponent(txtAnaHesap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHesaplar))
							.addGap(53)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblKartGrubu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(cbKartGrubu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(cbSube, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblSubeKodu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGap(72)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtKartNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblKartNo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblKartGecerlilikTarihi, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(ftxtKartGecerlilikTarihi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(78)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKartCvv, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtKartCvv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(78, Short.MAX_VALUE))
				);
				panel_2.setLayout(gl_panel_2);
			}
		}
	}

