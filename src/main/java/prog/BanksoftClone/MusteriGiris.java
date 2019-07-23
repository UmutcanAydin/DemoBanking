package prog.BanksoftClone;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.poi.EmptyFileException;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class MusteriGiris extends JDialog {

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
	private JComboBox<Object> cbSubeKodu;
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnGiris;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MusteriGiris dialog = new MusteriGiris();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void giris() throws Exception {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Müşteriler");
		Document musteri = new Document();
		ArrayList<Document> hesaplar = new ArrayList<Document>();
		ArrayList<Document> kartlar = new ArrayList<Document>();
		if(txtMusteriAdi.getText().equals("")) {
				throw new Exception("Lütfen Müşteri Adı Girin");
		}else if(txtMusteriSoyadı.getText().equals("")){		
				throw new Exception("Lütfen Müşteri Soyadı Girin");		
		}else if(txtMusteriNo.getText().equals("")){			
				throw new Exception("Lütfen Müşteri Numarası Girin");		
		}else if(txtTcNum.getText().equals("")){		
				throw new Exception("Lütfen Müşteri TC Numarası Girin");		
		}else if(txtTelefon.getText().equals("")){			
				throw new Exception("Lütfen Müşteri Telefonu Girin");			
		}else if(ftxtDogumTarihi.getText().equals("")){			
				throw new Exception("Lütfen Müşteri Doğum Tarihi Girin");			
		}else if(txtEposta.getText().equals("")){
				throw new Exception("Lütfen Müşteri Epostası Girin");			
		}else if(txtDogumYeri.getText().equals("")){			
				throw new Exception("Lütfen Müşteri Dopum Yeri Girin");			
		}else if(txtBabaAdi.getText().equals("")){			
				throw new Exception("Lütfen Müşteri Baba Adı Girin");		
		}else {
		musteri.append("isim",txtMusteriAdi.getText());
		musteri.append("soyisim", txtMusteriSoyadı.getText());
		musteri.append("tcno", txtTcNum.getText());
		musteri.append("telefon", txtTelefon.getText());
		musteri.append("babaadi", txtBabaAdi.getText());
		musteri.append("dogumyeri", txtDogumYeri.getText());
		musteri.append("dogumtarihi", ftxtDogumTarihi.getText());
		musteri.append("e-posta", txtEposta.getText());
		musteri.append("musterino", txtMusteriNo.getText());
		musteri.append("subekodu", cbSubeKodu.getSelectedItem());
		musteri.append("hesaplar", hesaplar);
		musteri.append("kartlar", kartlar);
		
		MusteriCollection.insertOne(musteri);
		
		model.addRow(new Object[]{txtMusteriAdi.getText(),txtMusteriSoyadı.getText(),txtTcNum.getText(),
								  txtTelefon.getText(),txtBabaAdi.getText(),txtDogumYeri.getText(),
								  ftxtDogumTarihi.getText(),txtEposta.getText(),txtMusteriNo.getText(),
								  cbSubeKodu.getSelectedItem()});
		}
	}
	
	public void temizle() {
		txtMusteriAdi.setText("");
		txtMusteriSoyadı.setText("");
		txtTcNum.setText("");
		txtTelefon.setText("");
		txtBabaAdi.setText("");
		txtDogumYeri.setText("");
		ftxtDogumTarihi.setText("");
		txtEposta.setText("");
		txtMusteriNo.setText("");
		cbSubeKodu.setSelectedIndex(0);
	}
	
	private void exceleyaz() {
		
	}
	/**
	 * Create the dialog.
	 */
	public MusteriGiris() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setToolTipText("");
			contentPanel.add(pnlButtons, BorderLayout.SOUTH);
			
			btnGiris = new JButton("Giriş");
			btnGiris.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					 try {
						giris();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						
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
			
			JButton btnYeniMusteri = new JButton("Yeni Müşteri");
			btnYeniMusteri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						try {
							giris();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						temizle();	
				}
			});
			pnlButtons.add(btnYeniMusteri);
			
			JButton btnExeleAktar = new JButton("Exel'e Aktar");
			pnlButtons.add(btnExeleAktar);
			
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
		lblMteriAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriAdi = new JTextField();
		lblMteriAd.setLabelFor(txtMusteriAdi);
		txtMusteriAdi.setColumns(10);
		
		JLabel lblMusteriSoyadi = new JLabel("Müşteri Soyadı:");
		lblMusteriSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriSoyadı = new JTextField();
		lblMusteriSoyadi.setLabelFor(txtMusteriSoyadı);
		txtMusteriSoyadı.setColumns(10);
		
		JLabel lblTcNumaras = new JLabel("TC Numarası:");
		lblTcNumaras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTcNum = new JTextField();
		lblTcNumaras.setLabelFor(txtTcNum);
		txtTcNum.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTelefon = new JTextField();
		lblTelefon.setLabelFor(txtTelefon);
		txtTelefon.setColumns(10);
		
		JLabel lblBabaAd = new JLabel("Baba Adı:");
		lblBabaAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtBabaAdi = new JTextField();
		txtBabaAdi.setColumns(10);
		
		JLabel lblDoumYeri = new JLabel("Doğum Yeri:");
		lblDoumYeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDogumYeri = new JTextField();
		lblDoumYeri.setLabelFor(txtDogumYeri);
		txtDogumYeri.setColumns(10);
		
		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi:");
		lblDoumTarihi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ftxtDogumTarihi = new JFormattedTextField();
		
		JLabel lblEpostaAdres = new JLabel("E-Posta Adresi:");
		lblEpostaAdres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEposta = new JTextField();
		txtEposta.setColumns(10);
		
		JLabel lblMteriNo = new JLabel("Müşteri No:");
		lblMteriNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMusteriNo = new JTextField();
		txtMusteriNo.setColumns(10);
		
		JLabel lblSubeKodu = new JLabel("Şube Kodu:");
		lblSubeKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cbSubeKodu = new JComboBox<Object>();
		lblSubeKodu.setLabelFor(cbSubeKodu);
		cbSubeKodu.setModel(new DefaultComboBoxModel<Object>(new String[] {"Sube1", "Sube2", "Sube3", "Sube4"}));
		GroupLayout gl_pnlInputs = new GroupLayout(pnlInputs);
		gl_pnlInputs.setHorizontalGroup(
			gl_pnlInputs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInputs.createSequentialGroup()
					.addGap(6)
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
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addComponent(lblSubeKodu, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbSubeKodu, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlInputs.createSequentialGroup()
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMusteriSoyadi)
								.addComponent(lblBabaAd)
								.addComponent(lblEpostaAdres))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtMusteriSoyadı)
								.addComponent(txtBabaAdi, Alignment.TRAILING)
								.addComponent(txtEposta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMteriNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTcNumaras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDoumYeri, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlInputs.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtTcNum)
								.addComponent(txtDogumYeri)
								.addComponent(txtMusteriNo, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))))
		);
		gl_pnlInputs.setVerticalGroup(
			gl_pnlInputs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInputs.createSequentialGroup()
					.addContainerGap()
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
						.addComponent(lblSubeKodu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbSubeKodu, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlInputs.setLayout(gl_pnlInputs);
		
		JPanel pnlTable = new JPanel();
		contentPanel.add(pnlTable, BorderLayout.CENTER);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
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
	
	
	
	
	public JButton getBtnGiris() {
		return btnGiris;
	}

	public JTextField getTxtMusteriAdi() {
		return txtMusteriAdi;
	}

	public void setTxtMusteriAdi(JTextField txtMusteriAdi) {
		this.txtMusteriAdi = txtMusteriAdi;
	}

	public JTextField getTxtMusteriSoyadı() {
		return txtMusteriSoyadı;
	}

	public void setTxtMusteriSoyadı(JTextField txtMusteriSoyadı) {
		this.txtMusteriSoyadı = txtMusteriSoyadı;
	}

	public JTextField getTxtTcNum() {
		return txtTcNum;
	}

	public void setTxtTcNum(JTextField txtTcNum) {
		this.txtTcNum = txtTcNum;
	}

	public JTextField getTxtTelefon() {
		return txtTelefon;
	}

	public void setTxtTelefon(JTextField txtTelefon) {
		this.txtTelefon = txtTelefon;
	}

	public JTextField getTxtBabaAdi() {
		return txtBabaAdi;
	}

	public void setTxtBabaAdi(JTextField txtBabaAdi) {
		this.txtBabaAdi = txtBabaAdi;
	}

	public JTextField getTxtDogumYeri() {
		return txtDogumYeri;
	}

	public void setTxtDogumYeri(JTextField txtDogumYeri) {
		this.txtDogumYeri = txtDogumYeri;
	}

	public JTextField getTxtEposta() {
		return txtEposta;
	}

	public void setTxtEposta(JTextField txtEposta) {
		this.txtEposta = txtEposta;
	}

	public JTextField getTxtMusteriNo() {
		return txtMusteriNo;
	}

	public void setTxtMusteriNo(JTextField txtMusteriNo) {
		this.txtMusteriNo = txtMusteriNo;
	}

	public JFormattedTextField getFtxtDogumTarihi() {
		return ftxtDogumTarihi;
	}

	public void setFtxtDogumTarihi(JFormattedTextField ftxtDogumTarihi) {
		this.ftxtDogumTarihi = ftxtDogumTarihi;
	}

	public void setBtnGiris(JButton btnGiris) {
		this.btnGiris = btnGiris;
	}

}
