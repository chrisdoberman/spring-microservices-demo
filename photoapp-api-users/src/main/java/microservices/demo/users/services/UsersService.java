package microservices.demo.users.services;

import microservices.demo.users.shared.UserDto;

public interface UsersService {

    UserDto createUser(UserDto userDetails);
}
