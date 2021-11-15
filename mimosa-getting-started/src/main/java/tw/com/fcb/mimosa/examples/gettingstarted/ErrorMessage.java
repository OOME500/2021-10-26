package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Translated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "errorCode")                //不一定要定義
public class ErrorMessage implements Translated{
	@Id
	String code;
	@NotBlank
	String category;
	@NotBlank
	String translation;
	
	@Override
	public String getTranslation() {
		return translation;
	}
	
	@Override
	public String getCategory() {
		return category;
	}
	
	@Override
	public String getCode() {
		return code;
	}

}
