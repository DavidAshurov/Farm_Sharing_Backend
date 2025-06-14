package farm_sharing.farm.service;

import farm_sharing.farm.dto.FarmDto;
import farm_sharing.farm.dto.NewFarmDto;

import java.util.List;

public interface FarmService {
    boolean register(NewFarmDto dto);

    FarmDto findById(Long id);

    List<FarmDto> getAllFarms();

    FarmDto updateFarmName(Long id, String name);

    boolean deleteFarm(Long id);
}
