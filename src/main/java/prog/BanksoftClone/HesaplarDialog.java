package prog.BanksoftClone;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HesaplarDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String hesapsube;
	private String hesapno;
	private String hesapnum;
	private String dovizcinsi;
	static KartGiris kg = new KartGiris();
	private static final Logger logger = LogManager.getLogger(HesaplarDialog.class);

	public String getHesapno() {
		return hesapno;
	}

	public void setHesapno(String hesapno) {
		this.hesapno = hesapno;
	}

	/**
	 * Aplikasyonu Çalıştırır.
	 */
	public static void main(String[] args) {
		try {
			HesaplarDialog dialog = new HesaplarDialog(kg.getTxtMustNo().getText());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dialogu Oluşturur.
	 * @param hesapmuststring 
	 */
	@SuppressWarnings("unchecked")
	public HesaplarDialog(String hesapmuststring) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 626);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setModel(model = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Hesap No", "Hesap \u015Eubesi", "D\u00F6viz Cinsi"
					}
				));
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		}
		
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Hesaplar");
		BasicDBObject whereQuery = new BasicDBObject();
		
			whereQuery.put("musterino", hesapmuststring);
			
			FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
			for (Document d : iterable) {				
				
				hesapno = (String) d.get("hesapno");
				hesapsube = (String) d.get("hesapşubesi");
				dovizcinsi = (String) d.get("dövizcinsi");
				model.addRow(new Object[]{hesapno,hesapsube,dovizcinsi});
				
			}
		
			
		mongoClient.close();
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSec = new JButton("Seç");
				btnSec.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						logger.info("'Seç' butonuna basıldı.");
						hesapnum = (String) model.getValueAt(table.getSelectedRow(), 0);
						setVisible(false);
					}
				});
				buttonPane.add(btnSec);
				getRootPane().setDefaultButton(btnSec);
			}
			{
				JButton btnCikis = new JButton("Çıkış");
				btnCikis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						logger.info("'HesaplarDialog' sayfasından çıkıldı.");
						setVisible(false);
					}
				});
				buttonPane.add(btnCikis);
			}
		}
	}
	
	public String getHesapnum() {
		return hesapnum;
	}

}
