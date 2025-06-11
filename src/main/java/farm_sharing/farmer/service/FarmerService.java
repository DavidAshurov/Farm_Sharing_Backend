package farm_sharing.farmer.service;

import farm_sharing.farmer.dto.FarmerDto;
import farm_sharing.farmer.dto.NewFarmerDto;

import java.util.List;

public interface FarmerService {
    boolean register(NewFarmerDto newFarmerDto);

    FarmerDto findById(Long id);

    List<FarmerDto> getAllFarmers();

    FarmerDto updateCompanyName(Long id,String name);

    boolean deleteFarmer(Long id);
}
