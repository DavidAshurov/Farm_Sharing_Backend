package farm_sharing.farmer.service;

import farm_sharing.farmer.dao.FarmerRepository;
import farm_sharing.farmer.dto.FarmerDto;
import farm_sharing.farmer.dto.NewFarmerDto;
import farm_sharing.farmer.dto.exceptions.FarmerNotFoundException;
import farm_sharing.farmer.model.Farmer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    final FarmerRepository farmerRepository;
    final ModelMapper modelMapper;

    @Override
    public boolean register(NewFarmerDto newFarmerDto) {
        if (farmerRepository.existsFarmerByEmail(newFarmerDto.getEmail()) ||
                farmerRepository.existsFarmerByPhoneNumber(newFarmerDto.getPhoneNumber())) {
            return false;
        }
        farmerRepository.save(modelMapper.map(newFarmerDto, Farmer.class));
        return true;
    }

    @Override
    public FarmerDto findById(Long id) {
        Farmer farmer = farmerRepository.findById(id).orElseThrow(FarmerNotFoundException::new);
        return modelMapper.map(farmer, FarmerDto.class);
    }

    @Override
    public List<FarmerDto> getAllFarmers() {
        return farmerRepository.findAll().stream()
                .map(farmer -> modelMapper.map(farmer, FarmerDto.class))
                .toList();
    }

    @Override
    public FarmerDto updateCompanyName(Long id,String name) {
        Farmer farmer = farmerRepository.findById(id).orElseThrow(FarmerNotFoundException::new);
        if (name != null) {
            farmer.setCompanyName(name);
        }
        farmerRepository.save(farmer);
        return modelMapper.map(farmer,FarmerDto.class);
    }

    @Override
    public boolean deleteFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id).orElseThrow(FarmerNotFoundException::new);
        farmerRepository.delete(farmer);
        return true;
    }
}
