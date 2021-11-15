package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Collection;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;
import tw.com.fcb.mimosa.http.APIErrorT9nException;
import tw.com.fcb.mimosa.tracing.Traced;

@Traced
@RequiredArgsConstructor
@Service
public class UserService {
	final UserRepository repository;
	final UserDtoMapper mapper;
	
	public Collection<User> getUsers(){
		return repository.findAll();
	}
	
	public User createUser(CreateUserDto user) {
		return repository.save(mapper.createUser(user));
	}
	
	public User replaceUser(Long id, ReplaceUserDto user) {
		return repository.findById(id).map(db -> {
		      mapper.copyUser(user, db);
		      return db;
		}).map(repository::save)
				.orElseThrow(() -> new IllegalArgumentException("Id " + id + " not found"));
	}
	
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
	
	public User getByName(String name) {
		return repository.findByName(name)
				.orElseThrow(() -> {
					//return new APIErrorT9nException(err -> err.term(MyErrorCode.NAME_NOT_FOUND));
					return new APIErrorT9nException(err -> err.term(MyErrorCode.NAME_NOT_FOUND));
					//return new APIErrorException(err -> err.code("ERR1").message("name not found"));
				});
	}
}
