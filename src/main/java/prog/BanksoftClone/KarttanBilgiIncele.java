package prog.BanksoftClone;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;

public class KarttanBilgiIncele extends JDialog {
	private JTextField txtKartNo;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private String musterino;
	private String baglihesap;
	private String kartgrubu;
	private String subekodu;
	private String kartno;
	private String kartgecerliliktarihi;
	private String kartccv;
	private String eticaret;
	private String hesapno;
	private String hesapsubesi;
	private String dovizcinsi;
	private String isim;
	private String soyisim; 
	private String tcno;
	private String telefon;
	private String babaadi;
	private String dogumyeri;
	private String dogumtarihi;
	private String eposta;
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel model_1 = new DefaultTableModel();
	private DefaultTableModel model_2 = new DefaultTableModel();

	/**
	 * Aplikasyonu çalıştırır.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KarttanBilgiIncele dialog = new KarttanBilgiIncele();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Kart numarasını kullanarak,veri tabanından kart bilgilerini getirir.
	 */
	public void kartgetir() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Kartlar");
		
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("kartno", txtKartNo.getText());
		
		FindIterable<Document> iterable2 = MusteriCollection.find(whereQuery);
		for (Document d : iterable2) {
			musterino = (String) d.get("musterino");
		}
		
		BasicDBObject whereQuery2 = new BasicDBObject();
		
		whereQuery2.put("musterino", musterino);
			
		FindIterable<Document> iterable = MusteriCollection.find(whereQuery2);
		for (Document d : iterable) {
			baglihesap = (String) d.get("bağlıhesap");
			kartgrubu = (String) d.get("kartgrubu");
			subekodu = (String) d.get("subekodu");
			kartno = (String) d.get("kartno");
			kartgecerliliktarihi = (String) d.get("kartgecerliliktarihi");
			kartccv = (String) d.get("kartccv");
			eticaret = (String) d.get("eticaret");
			model_2.addRow(new Object[] {baglihesap,kartgrubu,subekodu,kartno,kartgecerliliktarihi,kartccv,eticaret});
		}
	}
	
	/**
	 * Müşteri numarasını kullanarak,veri tabanından hesap bilgilerini getirir.
	 */
	public void hesapgetir() {
		
			MongoClient mongoClient = MongoClients.create();
			MongoDatabase database = mongoClient.getDatabase("Banksoft");
			MongoCollection<Document> MusteriCollection = database.getCollection("Hesaplar");
			
			BasicDBObject whereQuery = new BasicDBObject();
			
			whereQuery.put("musterino", musterino);
				
			FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
			for (Document d : iterable) {
				hesapno = (String) d.get("hesapno");
				hesapsubesi = (String) d.get("hesapşubesi");
				dovizcinsi = (String) d.get("dövizcinsi");
				model_1.addRow(new Object[] {hesapno,hesapsubesi,dovizcinsi});
			}
		
	}
	
	/**
	 * Müşteri numarasını kullanarak,veri tabanından müşteri bilgilerini getirir.
	 */
	public void musterigetir() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Müşteriler");
		
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("musterino", musterino);
			
		FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
		for (Document d : iterable) {
			isim = (String) d.get("isim");
			soyisim = (String) d.get("soyisim");
			tcno = (String) d.get("tcno");
			telefon = (String) d.get("telefon");
			babaadi = (String) d.get("babaadi");
			dogumyeri = (String) d.get("dogumyeri");
			dogumtarihi = (String) d.get("dogumtarihi");
			eposta = (String) d.get("e-posta");
			model.addRow(new Object[]{isim,soyisim,tcno,telefon,babaadi,dogumyeri,dogumtarihi,eposta,musterino,subekodu});
		}
	}
	
	/**
	 * Dialogu oluşturur.
	 */
	public KarttanBilgiIncele() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 532);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblKartNo = new JLabel("Kart No:");
		lblKartNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtKartNo = new JTextField();
		txtKartNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtKartNo.setColumns(10);
		
		JButton btnGetir = new JButton("Getir");
		btnGetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				model_1.setRowCount(0);
				model_2.setRowCount(0);
				kartgetir();
				hesapgetir();
				musterigetir();
			}
		});
		btnGetir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(308)
					.addComponent(lblKartNo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtKartNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGetir, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(320, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKartNo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtKartNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetir)))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		
		table_2 = new JTable();
		table_2.setModel(model_2 = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ba\u011Fl\u0131 Hesap", "Kart Grubu", "\u015Eube Kodu", "Kart No", "Ge\u00E7erlilik Tarihi", "CCV", "E-Ticaret Durumu"
			}
		));
		table_2.setFillsViewportHeight(true);
		table_2.getTableHeader().setReorderingAllowed(false);
		scrollPane_2.setViewportView(table_2);
		
		table_1 = new JTable();
		table_1.setModel(model_1 = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hesap No", "Hesap \u015Eubesi", "D\u00F6viz Cinsi"
			}
		));
		table_1.setFillsViewportHeight(true);
		table_1.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ad", "Soyad", "TC No", "Telefon", "Baba Ad\u0131", "Do\u011Fum Yeri", "Do\u011Fum Tarihi", "E-Posta", "M\u00FC\u015Fteri No", "\u015Eube Kodu"
			}
		));
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);


	}
}
