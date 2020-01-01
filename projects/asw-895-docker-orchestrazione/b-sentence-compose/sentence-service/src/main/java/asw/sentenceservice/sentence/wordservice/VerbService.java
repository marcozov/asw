package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class VerbService implements WordService {
	
	private final Logger logger = Logger.getLogger(VerbService.class.toString());
	
	@Autowired
	private VerbClient verbClient;

	@Override
	@HystrixCommand(fallbackMethod="getFallbackWord")
	public String getWord() {
		return verbClient.getWord();
	}

	public String getFallbackWord() {
		return "does";
	}
}
