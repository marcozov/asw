package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class SubjectService implements WordService {
	
	private final Logger logger = Logger.getLogger(SubjectService.class.toString());
	
	@Value("${word.subject.uri}")
	private String subjectUri;
	
	@Autowired
	private WordClient wordClient;

	@Override
	public String getWord() {
		return wordClient.getWord(subjectUri);
	}

}
