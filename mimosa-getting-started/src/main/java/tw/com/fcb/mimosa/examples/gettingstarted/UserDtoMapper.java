package tw.com.fcb.mimosa.examples.gettingstarted;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserDtoMapper {

	@Mapping(source = "name" , target = "userName")
	@Mapping(source = "uuid" , target = "memberId")
	UserDto from(User user);
}
