package farm_sharing.configuration;

import farm_sharing.shared.images.service.ImageService;
import farm_sharing.user.dto.UserDto;
import farm_sharing.user.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {
    final ImageService imageService;

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());

        modelMapper.typeMap(User.class, UserDto.class)
                .addMappings(mapper ->
                        mapper.using(ctx -> {
                                    String key = (String) ctx.getSource();
                                    return key == null ? null : imageService.toPublicUrl(key);
                                })
                                .map(User::getAvatar, UserDto::setAvatar)
                );
        return modelMapper;
    }
}
