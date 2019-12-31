package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class VerbService implements WordService {
	
	private final Logger logger = Logger.getLogger(VerbService.class.toString());
	
	@Autowired
	private WordClient wordClient;

	@Override
	public String getWord() {
		return wordClient.getWord("verb");
	}

}
