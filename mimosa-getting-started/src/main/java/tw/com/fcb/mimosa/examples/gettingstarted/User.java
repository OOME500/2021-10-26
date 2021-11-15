package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "user")                //不一定要定義
public class User {
	@Id                              //JPA一定要定義ID
	@GeneratedValue                  //自動產生ID
	Long id;
	@Column(name = "user_name")
	String name;
	int age;
}
