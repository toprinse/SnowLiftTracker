package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainUserSnow;
import fr.esisar.snowlifttracker.model.UserSnow;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainUserSnowMapper;

@Singleton
@Named
public class PlainUserSnowMapperImpl implements PlainUserSnowMapper{

    @Override
    public DTOPlainUserSnow fromUserToDtoPlainUser(UserSnow user) {
        if ( user == null ) {
            return null;
        }

        DTOPlainUserSnow dTOPlainUserSnow = new DTOPlainUserSnow();

        dTOPlainUserSnow.id = user.id;
        dTOPlainUserSnow.firstname = user.firstname;
        dTOPlainUserSnow.lastname = user.lastname;
        dTOPlainUserSnow.email = user.email;
        dTOPlainUserSnow.login = user.login;
        dTOPlainUserSnow.password = user.password;
        dTOPlainUserSnow.permission = user.permission;

        return dTOPlainUserSnow;
    }

    @Override
    public UserSnow toUserFromDtoPlainUser(DTOPlainUserSnow dtoPlainUser) {
        if ( dtoPlainUser == null ) {
            return null;
        }

        UserSnow userSnow = new UserSnow();

        userSnow.id = dtoPlainUser.id;
        userSnow.firstname = dtoPlainUser.firstname;
        userSnow.lastname = dtoPlainUser.lastname;
        userSnow.email = dtoPlainUser.email;
        userSnow.login = dtoPlainUser.login;
        userSnow.password = dtoPlainUser.password;
        userSnow.permission = dtoPlainUser.permission;

        return userSnow;
    }
}
