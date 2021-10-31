package tw.com.fcb.mimosa.examples.gettingstarted;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getusers")
public class UserController {
	
	static List<User> users = new ArrayList<>();

	@Autowired
	UserDtoMapper mapper;
	
	@GetMapping("/User/")
	List<UserDto> getUser() {
//		User user = new User();
//		user.setAge(20);
//		user.setName("Tom");
//		return user;
//		return User.builder()
//				.age(18)
//				.name("Tom")
//				.build();
		
		List<UserDto> dtos = new ArrayList<>();
		for(int i=0;i<users.size();i++) {
			UserDto dto = new UserDto();
			//dto.setName(users.get(i).getName());
			UserDto dtosmapper = mapper.from(users.get(i));
			dtos.add(dtosmapper);
			
		}
		//BeanUtils.copyPorperties->Runtime效能問題
		//after JAVA8
		//users.stream().map(user -> UserDto.builder().name(user.getName()).build()).collect(Collectors.toList());
		
		return dtos;
	}
	
	@PostMapping("/createUser")
	void createUser(@RequestBody User user) {
		user.setUuid(UUID.randomUUID().toString());
		user.setCreateDate(LocalDate.now());
		users.add(user);
	}
	
	
	//Github repository name:2021-10-26
	//TODO:修改使用者PUT
	@PostMapping("/modifyUser")
	String modifyUser(@RequestBody User user) {
		String result = "";
		int count = 0;
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUuid().equals(user.getUuid())) {
				users.set(i, user);
				users.get(i).setModifyDate(LocalDate.now());
				result = "Modify success!";
				count++;
			}
		}
		if(count == 0) {
			result = "Invalid uuid!";
		}
		return result;
	}
	
	//TODO:刪除使用者DELETE
	@PostMapping("/deleteUser")
	String deleteUser(@RequestBody User user) {
		String result = "";
		int count = 0;
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUuid().equals(user.getUuid())) {
				users.remove(i);
				result = "Delete success!";
				count++;
			}
		}
		if(count == 0) {
			result = "Invalid uuid!";
		}
		return result;
	}
}
