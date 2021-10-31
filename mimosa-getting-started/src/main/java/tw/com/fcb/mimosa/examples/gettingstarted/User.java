package tw.com.fcb.mimosa.examples.gettingstarted;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Getter
//@Setter
//上面等於Data,若使用Builder要加NoArg跟AllArg
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	String uuid;
	String name;
	int age;
	LocalDate createDate;
	LocalDate ModifyDate;
	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
}
