package rastus;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import fr.snekkja.cours.rastus.domain.Boisson;

public class TestXML {

	@Test
	public void test() throws JAXBException, IOException {
		
		JAXBContext context = JAXBContext.newInstance(Boisson.class);
		
		final Boisson cafe = new Boisson(1, "Café", BigDecimal.valueOf(0.5));
		
		// Nous créons une référence à un fichier temporaire.
		final File fichierTemporaire = File.createTempFile("glouglou", ".xml");
		
		context.createMarshaller().marshal(cafe, fichierTemporaire);
		final Boisson cafeLu = (Boisson) context.createUnmarshaller().unmarshal(fichierTemporaire);
		
		assert cafeLu.equals(cafe);
	}
	
}
