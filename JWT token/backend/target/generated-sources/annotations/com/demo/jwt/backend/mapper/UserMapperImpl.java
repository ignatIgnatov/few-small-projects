package com.demo.jwt.backend.mapper;

import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.model.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-05T15:21:26+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( userEntity.getId() );
        userDto.firstName( userEntity.getFirstName() );
        userDto.lastName( userEntity.getLastName() );
        userDto.login( userEntity.getLogin() );

        return userDto.build();
    }

    @Override
    public UserEntity signUpToUser(SignUpDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.firstName( userDto.getFirstName() );
        userEntity.lastName( userDto.getLastName() );
        userEntity.login( userDto.getLogin() );

        return userEntity.build();
    }
}
