package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class ObjectService implements WordService {
	
	private final Logger logger = Logger.getLogger(ObjectService.class.toString());
	
	@Value("${word.object.uri}") // http://localhost:8081
	private String objectUri;
	
	@Autowired
	private WordClient wordClient;

	@Override
	public String getWord() {
		return wordClient.getWord(objectUri);
	}

}
