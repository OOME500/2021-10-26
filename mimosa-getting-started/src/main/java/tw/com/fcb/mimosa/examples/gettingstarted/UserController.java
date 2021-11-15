package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorT9nException;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

@Tag(name="使用者",description = "User")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	static List<User> users = new ArrayList<>();
	final UserDtoMapper mapper;
	final UserRepository repository;
	final UserService userService;
	
	@GetMapping("/User/")
	@ApiResponse
	APIResponse<List<UserDto>> getUser() {
//		return User.builder().age(18).name("Tom").build();透過Builder建立物件
		
		List<UserDto> dto = userService.getUsers().stream().map(mapper::from).collect(Collectors.toList());
		if(dto.isEmpty()) {
			throw new APIErrorT9nException(err -> err.term(MyErrorCode.DATA_NOT_FOUND));
			//return APIResponse.error(err -> err.code("").message(""));
		}
		return APIResponse.success();
		
		//before JAVA8
//		List<UserDto> dtos = new ArrayList<>();
//		for(int i=0;i<users.size();i++) {
//			UserDto dtosmapper = mapper.from(users.get(i));
//			dtos.add(dtosmapper);
//			
//		}
		
		//after JAVA8
		//users.stream().map(user -> UserDto.builder().name(user.getName()).build()).collect(Collectors.toList());
		
//		return dtos;
	}
	
	
	@ApiResponse
	@PostMapping("/createuser")
	APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> createDto) {
		return APIResponse.success(userService.createUser(createDto.getBody())).map(User::getId);
	}
	
	
	//Github repository name:2021-10-26
	//TODO:修改使用者PUT
	@PutMapping("/{id}")
	void modifyUser(@Min(0) @PathVariable("id") Long id, @Validated @RequestBody ReplaceUserDto replaceDto) {
		userService.replaceUser(id, replaceDto);
	}
	
	
	//TODO:刪除使用者DELETE
	//delete users->/delete/users/uuid
	@DeleteMapping("/{id}")
	void deleteUser(@Min(0) @PathVariable("id") Long uuid) {
		userService.deleteUser(uuid);
		//users.removeIf(user -> user.getUuid().equals(uuid));
	}
	
	
	@ApiResponse
	@PostMapping("/name/")
	APIResponse<User> getByName(@RequestBody APIRequest<String> name){
		return APIResponse.success(userService.getByName(name.getBody()));
	}
	

}
