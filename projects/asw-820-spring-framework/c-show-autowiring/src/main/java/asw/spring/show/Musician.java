package asw.spring.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="hendrix")
public class Musician implements Artist {
	String name;
	Instrument instrument;
	
	@Autowired
	public Musician(@Value("${show.hendrix.name}") String name, Instrument instrument) {
		this.name = name;
		this.instrument = instrument;
	}

	@Override
	public String perform() {
		return "I'm " + this.name + ": " + this.instrument.play();
	}

	
}
