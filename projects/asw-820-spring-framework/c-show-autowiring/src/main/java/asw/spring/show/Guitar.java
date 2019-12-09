package asw.spring.show;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="stratocaster")
public class Guitar implements Instrument {
	private String sound;
	
	@Value("${show.stratocaster.sound}")
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	@Override
	public String play() {
		return this.sound;
	}

}
