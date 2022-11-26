import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.io.IOException;

public class UrunAramaTest extends MainTest{

    @Test
    public void Test() throws CsvValidationException, IOException {
        String aranacakUrun = "Ceket";

        anaSayfaElements.setUrun(aranacakUrun);
        anaSayfaElements.cerezKabulEt();
        anaSayfaElements.aramaYap();
        anaSayfaElements.sayfaDegistir();
        anaSayfaElements.indirimliUrunBul();
        anaSayfaElements.sepeteGit();
        anaSayfaElements.girisYap();
        anaSayfaElements.anaSayfayaDon();
        anaSayfaElements.sepetKontrol();
    }

}
