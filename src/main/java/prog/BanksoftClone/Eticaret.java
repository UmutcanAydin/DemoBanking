package prog.BanksoftClone;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.Dialog.ModalityType;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Eticaret extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEtKartNo;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnAcik;
	private JRadioButton rdbtnKapali;
	private Object id;
	private String eticaret;
	
	/**
	 * Aplikasyonu çalıştırır.
	 */
	public static void main(String[] args) {
		try {
			Eticaret dialog = new Eticaret();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

	/**
	 * Hangi kartın e-ticaret durumunun güncelleneceğini bulur.
	 */
	public void bul() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Kartlar");
		BasicDBObject whereQuery = new BasicDBObject();
		
			whereQuery.put("kartno", txtEtKartNo.getText());
			
			FindIterable<Document> iterable = MusteriCollection.find(whereQuery);
			for (Document d : iterable) {
				id = d.get("_id");
				eticaret = (String) d.get("eticaret");
			}
			
			rdbtnAcik.setEnabled(true);
			rdbtnKapali.setEnabled(true);
			System.out.println(id);
	}
	
	/**
	 * E-ticaret durumunu günceller.
	 */
	public void guncelle(){
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("Banksoft");
		MongoCollection<Document> MusteriCollection = database.getCollection("Kartlar");
		Document query = new Document();
        query.append("_id", id);
        Document updateFields = new Document();
        updateFields.append("eticaret", getSelectedButtonText(bg));
        			
        Document setQuery = new Document();
        setQuery.append("$set", updateFields);
    
        MusteriCollection.updateOne(query, setQuery);
      
	}
	/**
	 * Dialogu oluşturur.
	 */
	public Eticaret() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 939, 626);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblEticaretDurumu = new JLabel("E-Ticaret Durumu");
		lblEticaretDurumu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEticaretDurumu.setHorizontalAlignment(SwingConstants.CENTER);
		
		rdbtnAcik = new JRadioButton("Açık");
		rdbtnAcik.setEnabled(false);
		rdbtnAcik.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		rdbtnKapali = new JRadioButton("Kapalı");
		rdbtnKapali.setVerticalAlignment(SwingConstants.CENTER);
		rdbtnKapali.setHorizontalAlignment(SwingConstants.LEADING);
		rdbtnKapali.setFont(new Font("Dialog", Font.PLAIN, 17));
		rdbtnKapali.setSelected(true);
		rdbtnKapali.setEnabled(false);
		
		bg.add(rdbtnAcik);
		bg.add(rdbtnKapali);
		
		
		JLabel lblKartNo = new JLabel("Kart No:");
		lblKartNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEtKartNo = new JTextField();
		txtEtKartNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEtKartNo.setColumns(10);
		
		JButton btnBul = new JButton("Bul");
		btnBul.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 bul();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEticaretDurumu, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(316)
									.addComponent(rdbtnAcik)
									.addGap(21))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblKartNo)
									.addGap(34)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtEtKartNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(33)
									.addComponent(btnBul, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(158)
									.addComponent(rdbtnKapali)))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblEticaretDurumu)
					.addGap(102)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEtKartNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKartNo)
						.addComponent(btnBul))
					.addGap(68)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAcik)
						.addComponent(rdbtnKapali))
					.addContainerGap(264, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Tamam");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guncelle();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Çık");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
