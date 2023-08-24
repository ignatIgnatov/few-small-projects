package com.demo.jwt.backend.mapper;

import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.model.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T11:32:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setEmail( userEntity.getEmail() );

        return userDto;
    }

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDto.getId() );
        userEntity.setFirstName( userDto.getFirstName() );
        userEntity.setLastName( userDto.getLastName() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setEmail( userDto.getEmail() );

        return userEntity;
    }

    @Override
    public UserEntity signUpToUser(SignUpDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName( userDto.getFirstName() );
        userEntity.setLastName( userDto.getLastName() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setEmail( userDto.getEmail() );

        return userEntity;
    }
}
