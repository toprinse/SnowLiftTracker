package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainUserSnow;
import fr.esisar.snowlifttracker.model.UserSnow;

public interface PlainUserSnowMapper {
    // REMINDER: We didn't implement user model association so no mapping here.
    
    DTOPlainUserSnow fromUserToDtoPlainUser(UserSnow user);
    UserSnow toUserFromDtoPlainUser(DTOPlainUserSnow dtoPlainUser);
}
