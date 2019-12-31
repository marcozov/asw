package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class SubjectService implements WordService {
	
	private final Logger logger = Logger.getLogger(SubjectService.class.toString());
	
	@Autowired
	private SubjectClient subjectClient;

	@Override
	public String getWord() {
		return subjectClient.getWord();
	}

}
