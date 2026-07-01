package farm_sharing.configuration;

import farm_sharing.cart.dto.OfferInCartDto;
import farm_sharing.offer.dto.OfferDto;
import farm_sharing.offer.model.Offer;
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
                                    return key == null || key.isEmpty()
                                            ? null
                                            : imageService.toPublicUrl(key);
                                })
                                .map(User::getAvatar, UserDto::setAvatar)
                );
        modelMapper.typeMap(Offer.class, OfferDto.class)
                .addMappings(mapper ->
                        mapper.using(ctx -> {
                                    String key = (String) ctx.getSource();
                                    return key == null || key.isEmpty()
                                            ? null
                                            : imageService.toPublicUrl(key);
                                })
                                .map(Offer::getImage, OfferDto::setImage)
                )
                .addMappings(mapper ->
                        mapper.map(Offer::getAvailableAmount, OfferDto::setAmount));
        modelMapper.typeMap(Offer.class, OfferInCartDto.class)
                .addMappings(mapper ->
                        mapper.map(Offer::getAvailableAmount, OfferInCartDto::setAmount));
        return modelMapper;
    }
}
