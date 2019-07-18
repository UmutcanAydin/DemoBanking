package prog.BanksoftClone;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;


public class MusteriGuncelle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMusteriAdi;
	private JTextField txtMusteriSoyadı;
	private JTextField txtTcNum;
	private JTextField txtTelefon;
	private JTextField txtBabaAdi;
	private JTextField txtDogumYeri;
	private JTextField txtEposta;
	private JTextField txtMusteriNo;
	private JFormattedTextField ftxtDogumTarihi;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField txtMusteriBul;
	private JRadioButton rdbtnTckn;
	private ButtonGroup tcknveyamusteri;
	private JRadioButton rdbtnMusteri; 
	private JComboBox<Object> cbSubeKodu;
	String musterino;
	String isim;
	String soyisim; 
	String tcno;
	String telefon;
	String babaadi;
	String dogumyeri;
	String dogumtarihi;
	String eposta;
	String subekodu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MusteriGuncelle dialog = new MusteriGuncelle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Object objectId;
	private void bul() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Müşteriler");
		BasicDBObject whereQuery = new BasicDBObject();
		if(rdbtnMusteri.isSelected()) {
			whereQuery.put("musterino", txtMusteriBul.getText());
			
		}else if(rdbtnTckn.isSelected()) {
			whereQuery.put("tcno", txtMusteriBul.getText());
		}
		
		
		Document d = MusteriCollection.find(whereQuery).first();
		isim = (String) d.get("isim");
		soyisim = (String) d.get("soyisim");
		tcno = (String) d.get("tcno");
		telefon = (String) d.get("telefon");
		babaadi = (String) d.get("babaadi");
		dogumyeri = (String) d.get("dogumyeri");
		dogumtarihi = (String) d.get("dogumtarihi");
		eposta = (String) d.get("e-posta");
		musterino = (String) d.get("musterino");
		subekodu = (String) d.get("subekodu");
		model.addRow(new Object[]{isim,soyisim,tcno,telefon,babaadi,dogumyeri,dogumtarihi,eposta,musterino,subekodu});
		/*txtMusteriAdi.setEnabled(true);
		txtMusteriSoyadı.setEnabled(true);
		txtTcNum.setEnabled(true);
		txtTelefon.setEnabled(true);
		txtBabaAdi.setEnabled(true);
		txtDogumYeri.setEnabled(true);
		ftxtDogumTarihi.setEnabled(true);
		txtEposta.setEnabled(true);
		txtMusteriNo.setEnabled(true);
		cbSubeKodu.setEnabled(true);
		txtMusteriAdi.setText(isim);
		txtMusteriSoyadı.setText(soyisim);
		txtTcNum.setText(tcno);
		txtTelefon.setText(telefon);
		txtBabaAdi.setText(babaadi);
		txtDogumYeri.setText(dogumyeri);
		ftxtDogumTarihi.setText(dogumtarihi);
		txtEposta.setText(eposta);
		txtMusteriNo.setText(musterino);
		cbSubeKodu.setSelectedItem(subekodu);
	    objectId = (Object) d.get("_id");*/
	   
	}
	
	private void guncelle() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Müşteriler");
		Document query = new Document();
        query.append("_id", objectId);
        Document updateFields = new Document();
        updateFields.append("isim", txtMusteriAdi.getText())
        			.append("soyisim", txtMusteriSoyadı.getText())
        			.append("tcno", txtTcNum.getText())
        			.append("telefon", txtTelefon.getText())
        			.append("babaadi", txtBabaAdi.getText())
        			.append("dogumyeri", txtDogumYeri.getText())
        			.append("dogumtarihi", ftxtDogumTarihi.getText())
        			.append("eposta", txtEposta.getText())
        			.append("musterino", txtMusteriNo.getText())
        			.append("subekodu", cbSubeKodu.getSelectedItem());
        Document setQuery = new Document();
        setQuery.append("$set", updateFields);
        MusteriCollection.updateOne(query, setQuery);
        model.addRow(new Object[]{txtMusteriAdi.getText(),txtMusteriSoyadı.getText(),txtTcNum.getText(),
				  txtTelefon.getText(),txtBabaAdi.getText(),txtDogumYeri.getText(),
				  ftxtDogumTarihi.getText(),txtEposta.getText(),txtMusteriNo.getText(),
				  cbSubeKodu.getSelectedItem()});
	}
	
	private void temizle() {
		txtMusteriAdi.setText("");
		txtMusteriSoyadı.setText("");
		txtTcNum.setText("");
		txtTelefon.setText("");
		txtBabaAdi.setText("");
		txtDogumYeri.setText("");
		ftxtDogumTarihi.setText("");
		txtEposta.setText("");
		txtMusteriNo.setText("");
	}
	
	
	/**
	 * Create the dialog.
	 */
	public MusteriGuncelle() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 626);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setToolTipText("");
			contentPanel.add(pnlButtons, BorderLayout.SOUTH);
			
			JButton btnGiris = new JButton("Güncelle");
			btnGiris.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guncelle();
				}
			});
			pnlButtons.add(btnGiris);
			
			JButton btnTemizle = new JButton("Temizle");
			btnTemizle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					temizle();
				}
			});
			pnlButtons.add(btnTemizle);
			
			JButton btnCikis = new JButton("Çıkış");
			btnCikis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			pnlButtons.add(btnCikis);
		}
		
		JPanel pnlInputs = new JPanel();
		contentPanel.add(pnlInputs, BorderLayout.NORTH);
		
		JLabel lblMteriAd = new JLabel("Müşteri Adı:");
		lblMteriAd.setEnabled(false);
		lblMteriAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriAdi = new JTextField();
		txtMusteriAdi.setEnabled(false);
		lblMteriAd.setLabelFor(txtMusteriAdi);
		txtMusteriAdi.setColumns(10);
		
		JLabel lblMusteriSoyadi = new JLabel("Müşteri Soyadı:");
		lblMusteriSoyadi.setEnabled(false);
		lblMusteriSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriSoyadı = new JTextField();
		txtMusteriSoyadı.setEnabled(false);
		lblMusteriSoyadi.setLabelFor(txtMusteriSoyadı);
		txtMusteriSoyadı.setColumns(10);
		
		JLabel lblTcNumaras = new JLabel("TC Numarası:");
		lblTcNumaras.setEnabled(false);
		lblTcNumaras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTcNum = new JTextField();
		txtTcNum.setEnabled(false);
		lblTcNumaras.setLabelFor(txtTcNum);
		txtTcNum.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setEnabled(false);
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTelefon = new JTextField();
		txtTelefon.setEnabled(false);
		lblTelefon.setLabelFor(txtTelefon);
		txtTelefon.setColumns(10);
		
		JLabel lblBabaAd = new JLabel("Baba Adı:");
		lblBabaAd.setEnabled(false);
		lblBabaAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtBabaAdi = new JTextField();
		txtBabaAdi.setEnabled(false);
		txtBabaAdi.setColumns(10);
		
		JLabel lblDoumYeri = new JLabel("Doğum Yeri:");
		lblDoumYeri.setEnabled(false);
		lblDoumYeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDogumYeri = new JTextField();
		txtDogumYeri.setEnabled(false);
		lblDoumYeri.setLabelFor(txtDogumYeri);
		txtDogumYeri.setColumns(10);
		
		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi:");
		lblDoumTarihi.setEnabled(false);
		lblDoumTarihi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ftxtDogumTarihi = new JFormattedTextField();
		ftxtDogumTarihi.setEnabled(false);
		
		JLabel lblEpostaAdres = new JLabel("E-Posta Adresi:");
		lblEpostaAdres.setEnabled(false);
		lblEpostaAdres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEposta = new JTextField();
		txtEposta.setEnabled(false);
		txtEposta.setColumns(10);
		
		JLabel lblMteriNo = new JLabel("Müşteri No:");
		lblMteriNo.setEnabled(false);
		lblMteriNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriNo = new JTextField();
		txtMusteriNo.setEnabled(false);
		txtMusteriNo.setColumns(10);
		
		rdbtnTckn = new JRadioButton("TCKN");
		rdbtnTckn.setSelected(true);
		rdbtnTckn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		rdbtnMusteri = new JRadioButton("Müşteri");
		rdbtnMusteri.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		tcknveyamusteri = new ButtonGroup();
		tcknveyamusteri.add(rdbtnMusteri);
		tcknveyamusteri.add(rdbtnTckn);
		
		txtMusteriBul = new JTextField();
		txtMusteriBul.setColumns(10);
		
		JButton btnBul = new JButton("Bul");
		btnBul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bul();
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Lütfen Geçerli Bir Numara Giriniz.");
				}
			}
		});
		
		JLabel lblSubeKodu = new JLabel("Şube Kodu:");
		lblSubeKodu.setEnabled(false);
		lblSubeKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cbSubeKodu = new JComboBox<Object>();
		cbSubeKodu.setEnabled(false);
		cbSubeKodu.setModel(new DefaultComboBoxModel<Object>(new String[] {"Sube1", "Sube2", "Sube3", "Sube4"}));
		
		JRadioButton rdbtnubeKodu = new JRadioButton("Şube Kodu");
		rdbtnubeKodu.setSelected(true);
		rdbtnubeKodu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_pnlInputs = new GroupLayout(pnlInputs);
		gl_pnlInputs.setHorizontalGroup(
			gl_pnlInputs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInputs.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMteriAd)
								.addComponent(lblTelefon)
								.addComponent(lblDoumTarihi))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ftxtDogumTarihi, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(txtTelefon)
								.addComponent(txtMusteriAdi, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMusteriSoyadi)
								.addComponent(lblBabaAd)
								.addComponent(lblEpostaAdres)))
						.addComponent(lblSubeKodu, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addComponent(rdbtnubeKodu, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnTckn, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnMusteri, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbSubeKodu, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtMusteriBul, Alignment.TRAILING)
						.addComponent(txtMusteriSoyadı)
						.addComponent(txtBabaAdi, Alignment.TRAILING)
						.addComponent(txtEposta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMteriNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTcNumaras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDoumYeri, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtTcNum)
								.addComponent(txtDogumYeri)
								.addComponent(txtMusteriNo, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
						.addComponent(btnBul, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_pnlInputs.setVerticalGroup(
			gl_pnlInputs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInputs.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMusteriBul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBul)
						.addComponent(rdbtnMusteri)
						.addComponent(rdbtnTckn)
						.addComponent(rdbtnubeKodu, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTcNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTcNumaras))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDogumYeri, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDoumYeri))
							.addGap(12)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMusteriNo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMteriNo)))
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlInputs.createSequentialGroup()
									.addGap(3)
									.addComponent(lblMteriAd))
								.addGroup(gl_pnlInputs.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtMusteriSoyadı, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMusteriSoyadi)))
								.addComponent(txtMusteriAdi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefon)
								.addComponent(txtTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBabaAdi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBabaAd))
							.addGap(13)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtDogumTarihi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDoumTarihi)
								.addComponent(txtEposta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEpostaAdres))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlInputs.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubeKodu)
						.addComponent(cbSubeKodu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		pnlInputs.setLayout(gl_pnlInputs);
		
		JPanel pnlTable = new JPanel();
		contentPanel.add(pnlTable, BorderLayout.CENTER);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ad\u0131", "Soyad\u0131", "TC Numaras\u0131", "Telefon", "Baba Ad\u0131", "Do\u011Fum Yeri", "Do\u011Fum Tarihi", "E-Posta", "M\u00FC\u015Fteri No","\u015Eube Kodu" 
			}
		));
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}
}
