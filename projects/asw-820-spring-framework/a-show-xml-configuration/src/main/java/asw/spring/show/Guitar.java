package asw.spring.show;

public class Guitar implements Instrument {
	private String sound;

	public void setSound(String sound) {
		this.sound = sound;
	}
	
	@Override
	public String play() {
		return this.sound;
	}

}
