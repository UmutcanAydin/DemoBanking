package prog.BanksoftClone;

import static org.junit.Assert.*;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class Test extends TestCase {
	   protected double fValue1;
	   protected double fValue2;
	   
	   @org.junit.Before 
	   public void setUp() {
	      fValue1 = 2.0;
	      fValue2 = 3.0;
	   }
		
	   @org.junit.Test
	   public void testAdd() {
	      //count the number of test cases
	      System.out.println("No of Test Case = "+ this.countTestCases());
			
	      //test getName 
	      String name = this.getName();
	      System.out.println("Test Case Name = "+ name);

	      //test setName
	      this.setName("testNewAdd");
	      String newName = this.getName();
	      System.out.println("Updated Test Case Name = "+ newName);
	   }
		
	   //tearDown used to close the connection or clean up activities
	   public void tearDown(  ) {
	   }
	   
	   
	/*@org.junit.Test
	public void musterigir() throws Exception {
		MusteriGiris mgiris = new MusteriGiris();
		mgiris.getTxtBabaAdi().setText("a");
		mgiris.getTxtBabaAdi().setText("a");
		mgiris.getTxtDogumYeri().setText("a");
		mgiris.getTxtEposta().setText("a");
		mgiris.getTxtMusteriAdi().setText("a");
		mgiris.getTxtMusteriNo().setText("a");
		mgiris.getTxtMusteriSoyadı().setText("a");
		mgiris.getTxtTcNum().setText("a");
		mgiris.getTxtTelefon().setText("a");
		mgiris.getFtxtDogumTarihi().setText("a");
		assertNotEquals("",mgiris.getTxtBabaAdi().getText());
		assertNotEquals("",mgiris.getTxtDogumYeri().getText());
		assertNotEquals("",mgiris.getTxtEposta().getText());
		assertNotEquals("",mgiris.getTxtMusteriAdi().getText());
		assertNotEquals("",mgiris.getTxtMusteriNo().getText());
		assertNotEquals("",mgiris.getTxtMusteriSoyadı().getText());
		assertNotEquals("",mgiris.getTxtTcNum().getText());
		assertNotEquals("",mgiris.getTxtTelefon().getText());
		assertNotEquals("",mgiris.getFtxtDogumTarihi().getText());
		
	}
	
	@org.junit.Test
	public void musteriguncelle() {
		MusteriGuncelle mguncelle = new MusteriGuncelle();
		assertNotEquals("",mguncelle.getTxtBabaAdi().getText());
		assertNotEquals("",mguncelle.getTxtDogumYeri().getText());
		assertNotEquals("",mguncelle.getTxtEposta().getText());
		assertNotEquals("",mguncelle.getTxtMusteriAdi().getText());
		assertNotEquals("",mguncelle.getTxtMusteriNo().getText());
		assertNotEquals("",mguncelle.getTxtMusteriSoyadı().getText());
		assertNotEquals("",mguncelle.getTxtTcNum().getText());
		assertNotEquals("",mguncelle.getTxtTelefon().getText());
		assertNotEquals("",mguncelle.getFtxtDogumTarihi().getText());
		mguncelle.bul();			
	
	}*/
	

}
