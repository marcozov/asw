package asw.sentenceservice.sentence.wordservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import asw.sentenceservice.sentence.domain.WordService;

@Service
public class SubjectService implements WordService {
	
	private final Logger logger = Logger.getLogger(SubjectService.class.toString());
	
	@Autowired
	private SubjectClient subjectClient;

	@Override
	@HystrixCommand(fallbackMethod="getFallbackWord")
	public String getWord() {
		return subjectClient.getWord();
	}

	public String getFallbackWord() {
		return "Someone";
	}
}
