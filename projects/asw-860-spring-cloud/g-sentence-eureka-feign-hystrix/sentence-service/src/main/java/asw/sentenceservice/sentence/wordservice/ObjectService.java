package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class ObjectService implements WordService {
	
	private final Logger logger = Logger.getLogger(ObjectService.class.toString());
	
	@Autowired
	private ObjectClient objectClient;

	@Override
	@HystrixCommand(fallbackMethod="getFallbackWord")
	public String getWord() {
		return objectClient.getWord();
	}

	public String getFallbackWord() {
		return "something";
	}
}
