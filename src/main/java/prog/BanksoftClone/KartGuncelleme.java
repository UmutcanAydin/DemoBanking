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
import javax.swing.JOptionPane;

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
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class KartGuncelleme extends JDialog {
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
	private String tcno;
	private JFormattedTextField ftxtDogumTarihi;
	private JFormattedTextField ftxtKartGecerlilikTarihi;
	private JButton btnHesaplar;
	private JComboBox<Object> cbKartGrubu;
	private JComboBox<Object> cbSube;
	private JTextField txtHesapMustGetir;
	private JTable table;
	private JTable table_1;
	private JTextField txtHesapNo;
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel model2 = new DefaultTableModel();
	private JComboBox<Object> cbDovizCinsi;
	private JComboBox<Object> cbHesapSubesi;
	private JTabbedPane tabbedPane;
	private Object hesapId;
	private Object kartId;
	private String hesapno;
	private String hesapsubesi;
	private String dovizcinsi;
	private String anahesap;
	private String kartgrubu;
	private String sube;
	private String kartno;
	private String kartgecerliliktarihi;
	private String kartccv;
	private String kartMusteriNo;
	
	/**
	 * Aplikasyonu çalıştırır.
	 */
	public static void main(String[] args) {
		try {
			KartGuncelleme dialog = new KartGuncelleme();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Hesap numarası kullanarak veri tabanından hesap bilgisi getirir.
	 */
	public void HesapBul() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Hesaplar");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("hesapno", txtHesapMustGetir.getText());
			
		FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
		for (Document d : iterable) {
			hesapId = d.get("_id");
			hesapno = (String) d.get("hesapno");
			hesapsubesi = (String) d.get("hesapşubesi");
			dovizcinsi = (String) d.get("dövizcinsi");
		
		}
			
		model.addRow(new Object[]{hesapno,hesapsubesi,dovizcinsi});
		txtHesapNo.setEnabled(true);
		txtHesapNo.setEditable(true);
		txtHesapNo.setText(hesapno);
		cbHesapSubesi.setEnabled(true);
		cbHesapSubesi.setSelectedItem(hesapsubesi);
		cbDovizCinsi.setEnabled(true);
		cbDovizCinsi.setSelectedItem(dovizcinsi);
	}
	
	/**
	 * Veri tabanında varolan hesap bilgisini günceller.
	 */
	public void HesapGuncelle() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Hesaplar");
		Document query = new Document();
		query.append("_id", hesapId);
		Document updateFields = new Document();
        updateFields.append("hesapno", txtHesapNo.getText())
        			.append("hesapşubesi", cbHesapSubesi.getSelectedItem())
        			.append("dövizcinsi", cbDovizCinsi.getSelectedItem());
        
        Document setQuery = new Document();
        setQuery.append("$set", updateFields);

        MusteriCollection.updateOne(query, setQuery);
		
		model2.addRow(new Object[]{txtHesapNo.getText(),cbHesapSubesi.getSelectedItem(),cbDovizCinsi.getSelectedItem()});
	}
	

	/**
	 * Kart numarası kullanarak veri tabanından kart bilgisi getirir.
	 */
	public void KartBul() {
		
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Kartlar");
		BasicDBObject whereQuery = new BasicDBObject();
		
			whereQuery.put("kartno", txtMustGetir.getText());
				
			FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
			for (Document d : iterable) {
				kartMusteriNo = (String) d.get("musterino");
				kartId = d.get("_id");
				anahesap = (String) d.get("bağlıhesap");
				kartgrubu = (String) d.get("kartgrubu");
				sube = (String) d.get("subekodu");
				kartno = (String) d.get("kartno");
				kartgecerliliktarihi = (String) d.get("kartgecerliliktarihi");
				kartccv = (String) d.get("kartccv");
					
			}
			txtAnaHesap.setEnabled(true);
			txtAnaHesap.setText(anahesap);
			btnHesaplar.setEnabled(true);
			cbKartGrubu.setEnabled(true);
			cbKartGrubu.setSelectedItem(kartgrubu);
			cbSube.setEnabled(true);
			cbSube.setSelectedItem(sube);
			txtKartNo.setEnabled(true);
			txtKartNo.setText(kartno);
			ftxtKartGecerlilikTarihi.setEnabled(true);
			ftxtKartGecerlilikTarihi.setText(kartgecerliliktarihi);
			txtKartCvv.setEnabled(true);
			txtKartCvv.setText(kartccv);
			
		mongoClient.close();
		
		mongoClient = MongoClients.create();
		database = mongoClient.getDatabase("Banksoft");
		MusteriCollection = database.getCollection("Müşteriler");
		BasicDBObject whereQuery2 = new BasicDBObject();
		
			whereQuery2.put("musterino", kartMusteriNo);
			
			FindIterable<Document> iterable2 = MusteriCollection.find(whereQuery2);
			for (Document d : iterable2) {
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
		
	}
	
	/**
	 * Veri tabanında varolan kart bilgisini günceller.
	 */
	public void KartGir() throws Exception {
	
        MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Kartlar");
		if(txtAnaHesap.getText().equals("")) {
			throw new Exception("Lütfen Bir Hesap Numarası Girin.");
		}else if(txtKartNo.getText().equals("")) {
			throw new Exception("Lütfen Bir Kart Numarası Girin.");
		}
		else if(txtKartCvv.getText().equals("")) {
			throw new Exception("Lütfen Bir CCV Numarası Girin.");
		}else if(ftxtKartGecerlilikTarihi.getText().equals("")) {
			throw new Exception("Lütfen Bir Kart Geçerlilik Tarihi Girin.");
		}else {
	
		Document query = new Document();
		query.append("_id", kartId);	
		Document updateFields = new Document();
        updateFields.append("bağlıhesap", txtAnaHesap.getText())
        			.append("kartgrubu", cbKartGrubu.getSelectedItem())
        			.append("subekodu", cbSube.getSelectedItem())
			        .append("kartno", txtKartNo.getText())
					.append("kartgecerliliktarihi", ftxtKartGecerlilikTarihi.getText())
			        .append("kartccv", txtKartCvv.getText());
        
        Document setQuery = new Document();
        setQuery.append("$set", updateFields);

        MusteriCollection.updateOne(query, setQuery);
		}	
	}
	
	/**
	 * Kart bilgilerini temizler.
	 */
	public void KartTemizle() {
		txtMustGetir.setText("");
		txtAd.setText("");
		txtSoyad.setText("");
		txtMustNo.setText("");
		txtBabaAdi.setText("");
		txtDogumYeri.setText("");
		ftxtDogumTarihi.setText("");
		isim = "";
		soyisim = "";
		babaadi = "";
		dogumyeri = "";
		dogumtarihi = "";
		musterino = "";
		subekodu = "";
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
	 * Hesap bilgilerini temizler.
	 */
	public void HesapTemizle() {
		txtHesapNo.setText("");
		cbHesapSubesi.setSelectedIndex(0);
		cbDovizCinsi.setSelectedIndex(0);
		txtHesapNo.setEnabled(false);
		cbHesapSubesi.setEnabled(false);
		cbDovizCinsi.setEnabled(false);
	}
	
	/**
	 * Dialogu oluşturur.
	 */
	public KartGuncelleme() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 626);
		getContentPane().setLayout(new BorderLayout());
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			
			
				JPanel pnlKartGiris = new JPanel();
				tabbedPane.addTab("Kart Girişi", null, pnlKartGiris, null);
				pnlKartGiris.setLayout(new BorderLayout(0, 0));
				
				JPanel pnlHesapGiriş = new JPanel();
				tabbedPane.addTab("Hesap Girişi", null, pnlHesapGiriş, null);
				pnlHesapGiriş.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_3 = new JPanel();
				pnlHesapGiriş.add(panel_3, BorderLayout.SOUTH);
				
				JButton btnHesapGuncelle = new JButton("Güncelle");
				btnHesapGuncelle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtHesapNo.getText().equals("")) {
							HesapGuncelle();
						}else {
							JOptionPane.showMessageDialog(null, "Lütfen Formu Doldurun.");
						}
						
					}
				});
				panel_3.add(btnHesapGuncelle);
				
				JButton btnHesapTemizle = new JButton("Temizle");
				btnHesapTemizle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						HesapTemizle();
					}
				});
				panel_3.add(btnHesapTemizle);
				
				JButton btnHesapExel = new JButton("Exel'e Aktar");
				panel_3.add(btnHesapExel);
				
				JButton btnHesapCikis = new JButton("Çıkış");
				btnHesapCikis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				panel_3.add(btnHesapCikis);
				
				JPanel panel_4 = new JPanel();
				pnlHesapGiriş.add(panel_4, BorderLayout.NORTH);
				
				JLabel lblHesapNo_1 = new JLabel("Hesap No:");
				lblHesapNo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				txtHesapMustGetir = new JTextField();
				txtHesapMustGetir.setColumns(10);
				
				JButton btnHesapMustBul = new JButton("Bul");
				btnHesapMustBul.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtHesapMustGetir.getText().equals("")) {
							HesapBul();
						}else {
							JOptionPane.showMessageDialog(null, "Lütfen Müşteri Numarası Girin.");
						}
					}
				});
				GroupLayout gl_panel_4 = new GroupLayout(panel_4);
				gl_panel_4.setHorizontalGroup(
					gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(286)
							.addComponent(lblHesapNo_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHesapMustGetir, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnHesapMustBul)
							.addGap(317))
				);
				gl_panel_4.setVerticalGroup(
					gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtHesapMustGetir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHesapNo_1)
								.addComponent(btnHesapMustBul))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel_4.setLayout(gl_panel_4);
				
				JPanel panel_5 = new JPanel();
				pnlHesapGiriş.add(panel_5, BorderLayout.CENTER);
				
				JScrollPane scrollPane = new JScrollPane();
				
				JScrollPane scrollPane_1 = new JScrollPane();
				
				JLabel lblHesapNo = new JLabel("Hesap No:");
				lblHesapNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				txtHesapNo = new JTextField();
				txtHesapNo.setEnabled(false);
				txtHesapNo.setEditable(false);
				lblHesapNo.setLabelFor(txtHesapNo);
				txtHesapNo.setColumns(10);
				
				JLabel lblHesapSubesi = new JLabel("Hesap Şubesi:");
				lblHesapSubesi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				cbHesapSubesi = new JComboBox<Object>();
				cbHesapSubesi.setModel(new DefaultComboBoxModel<Object>(new String[] {"Sube1", "Sube2", "Sube3", "Sube4"}));
				cbHesapSubesi.setSelectedIndex(0);
				cbHesapSubesi.setEnabled(false);
				cbHesapSubesi.setEditable(false);
				
				JLabel lblDovizCinsi = new JLabel("Döviz Cinsi:");
				lblDovizCinsi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				cbDovizCinsi = new JComboBox<Object>();
				cbDovizCinsi.setModel(new DefaultComboBoxModel<Object>(new String[] {"TL", "DOLAR", "EURO"}));
				cbDovizCinsi.setSelectedIndex(0);
				cbDovizCinsi.setEnabled(false);
				cbDovizCinsi.setEditable(false);
				GroupLayout gl_panel_5 = new GroupLayout(panel_5);
				gl_panel_5.setHorizontalGroup(
					gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblHesapNo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtHesapNo, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addGap(69)
								.addComponent(lblHesapSubesi, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addGap(19))
							.addGroup(gl_panel_5.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cbHesapSubesi, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addGap(82)
								.addComponent(lblDovizCinsi)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cbDovizCinsi, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addGap(90))
				);
				gl_panel_5.setVerticalGroup(
					gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap(22, Short.MAX_VALUE)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHesapNo)
								.addComponent(txtHesapNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbHesapSubesi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHesapSubesi)
								.addComponent(lblDovizCinsi)
								.addComponent(cbDovizCinsi, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				
				table_1 = new JTable();
				table_1.setModel(model2 = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Hesap No", "Hesap \u015Eubesi", "D\u00F6viz  Cinsi"
					}
				));
				table_1.setFillsViewportHeight(true);
				scrollPane_1.setViewportView(table_1);
				table_1.getTableHeader().setReorderingAllowed(false);
				
				table = new JTable();
				table.setModel(model = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Hesap No", "Hesap Şubesi", "Döviz Cinsi"
					}
				));
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
				panel_5.setLayout(gl_panel_5);
				table.getTableHeader().setReorderingAllowed(false);
				
				JPanel panel = new JPanel();
				pnlKartGiris.add(panel, BorderLayout.NORTH);
				
				txtMustGetir = new JTextField();
				txtMustGetir.setColumns(10);
				
				JButton btnMustBul = new JButton("Bul");
				btnMustBul.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtMustGetir.getText().equals("")) {
							KartBul();
						}else {
							JOptionPane.showMessageDialog(null, "Lütfen Müşteri Numarası Girin.");
						}
					}
				});
				
				JLabel lblMteriNo = new JLabel("Kart No:");
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
				
				JButton btnKartGuncelle = new JButton("Güncelle");
				btnKartGuncelle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
								KartGir();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				});
				panel_1.add(btnKartGuncelle);
				
				JButton btnKartTemizle = new JButton("Temizle");
				btnKartTemizle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						KartTemizle();
					}
				});
				panel_1.add(btnKartTemizle);
				
				JButton btnKartCikis = new JButton("Çıkış");
				btnKartCikis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				panel_1.add(btnKartCikis);
				
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
				btnHesaplar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						HesaplarDialog anahd = new HesaplarDialog(txtMustNo.getText());
						anahd.setVisible(true);
						if(anahd.isVisible() == false) {
							txtAnaHesap.setText(anahd.getHesapno());
						}
					}
				});
				btnHesaplar.setEnabled(false);
				
				JLabel lblKartGrubu = new JLabel("Kart Grubu:");
				lblKartGrubu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				cbKartGrubu = new JComboBox<Object>();
				cbKartGrubu.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cbKartGrubu.setEnabled(false);
				cbKartGrubu.setModel(new DefaultComboBoxModel<Object>(new String[] {"Troy", "Platin", "Visa Electron", "Mastercard maestro"}));
				
				cbSube = new JComboBox<Object>();
				cbSube.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cbSube.setEnabled(false);
				cbSube.setModel(new DefaultComboBoxModel<Object>(new String[] {"Sube1", "Sube2", "Sube3", "Sube4"}));
				
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

	public JTextField getTxtAnaHesap() {
		return txtAnaHesap;
	}

	public void setTxtAnaHesap(JTextField txtAnaHesap) {
		this.txtAnaHesap = txtAnaHesap;
	}

	public JTextField getTxtMustNo() {
		return txtMustNo;
	}

	public void setTxtMustNo(JTextField txtMustNo) {
		this.txtMustNo = txtMustNo;
	}

	}

