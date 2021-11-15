package tw.com.fcb.mimosa.examples.gettingstarted;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;
import tw.com.fcb.mimosa.tracing.Traced;

@Tag(name="錯誤代碼",description = "ErrorMessage")
@Traced
@RequiredArgsConstructor
@RestController
@RequestMapping("/errormessage")
@ApiResponse
public class ErrorMessageController {
	
	final ErrorMessageService errorMsgService ;
	
	@GetMapping("/init/")
	void initErrorMessage() {
		errorMsgService.createMessage(ErrorMessage.builder().category("E").code("ERR1").translation("查無姓名").build());
		errorMsgService.createMessage(ErrorMessage.builder().category("E").code("ERR2").translation("查無ID").build());
		errorMsgService.createMessage(ErrorMessage.builder().category("E").code("ERR3").translation("查無資料").build());
	}
	
	@PostMapping("/errormessage/")
	APIResponse<ErrorMessage> createErrorMessage(@RequestBody APIRequest<ErrorMessage> errorMessage){
		return APIResponse.success(errorMsgService.createMessage(errorMessage.getBody()));
	}
	
}
