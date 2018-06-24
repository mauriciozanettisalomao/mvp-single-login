package br.com.nextel.integrator.job.processor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.item.ItemProcessor;

import br.com.nextel.integrator.db.model.Foto;

public class FotoProcessor implements ItemProcessor<Foto, Foto> {

	@Override
	public Foto process(Foto item) throws Exception {

		Path filePath = Paths.get("c://img_lights.jpg");
		byte[] fileContent = Files.readAllBytes(filePath);
		
	    //item.setFoto(fileContent);
		
	    return item;
	}

}
