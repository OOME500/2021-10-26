package tw.com.fcb.mimosa.examples.gettingstarted;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;

@RequiredArgsConstructor
@Service
public class ErrorMessageService {
	final ErrorMessageRepository errorMsgRepository;
	
	public ErrorMessage getMessageByCode(String code) {
		return errorMsgRepository.findById(code)
				.orElseThrow(() -> {
					return new APIErrorException(err -> err.code("ERR0").message("Error code not found"));
				});
	}
	
	public ErrorMessage createMessage(ErrorMessage errorMessage) {
		return errorMsgRepository.save(errorMessage);
	}

}
