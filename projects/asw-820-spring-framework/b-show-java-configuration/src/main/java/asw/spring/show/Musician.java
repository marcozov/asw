package asw.spring.show;

public class Musician implements Artist {
	String name;
	Instrument instrument;
	
	public Musician(String name, Instrument instrument) {
		this.name = name;
		this.instrument = instrument;
	}

	@Override
	public String perform() {
		return "I'm " + this.name + ": " + this.instrument.play();
	}

	
}
