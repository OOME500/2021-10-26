package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Term;

@Getter
@RequiredArgsConstructor
public enum MyErrorCode implements Term{
	
	NAME_NOT_FOUND("E","ERR1"),
	ID_NOT_FOUND("E","ERR2"),
	DATA_NOT_FOUND("E","ERR3"),
	;
	
	//TODO:把ERROR CODE及說明從資料庫取出並回應
	final String category;
	final String code;
}
