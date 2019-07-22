package prog.BanksoftClone;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void musterigir() throws Exception {
		MusteriGiris mgiris = new MusteriGiris();
		/*mgiris.getTxtBabaAdi().setText("a");
		mgiris.getTxtBabaAdi().setText("a");
		mgiris.getTxtDogumYeri().setText("a");
		mgiris.getTxtEposta().setText("a");
		mgiris.getTxtMusteriAdi().setText("a");
		mgiris.getTxtMusteriNo().setText("a");
		mgiris.getTxtMusteriSoyadı().setText("a");
		mgiris.getTxtTcNum().setText("a");
		mgiris.getTxtTelefon().setText("a");
		mgiris.getFtxtDogumTarihi().setText("a");*/
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
		mguncelle.bul();
		
	}

}
