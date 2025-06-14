package farm_sharing.farm.service;

import farm_sharing.farm.dao.FarmRepository;
import farm_sharing.farm.dto.FarmDto;
import farm_sharing.farm.dto.NewFarmDto;
import farm_sharing.farm.dto.exceptions.FarmNotFoundException;
import farm_sharing.farm.model.Farm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    final FarmRepository farmRepository;
    final ModelMapper modelMapper;

    @Transactional
    @Override
    public boolean register(NewFarmDto dto) {
        if (farmRepository.existsFarmByEmail(dto.getEmail()) ||
                farmRepository.existsFarmByPhoneNumber(dto.getPhoneNumber())) {
            return false;
        }
        farmRepository.save(modelMapper.map(dto, Farm.class));
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public FarmDto findById(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
        return modelMapper.map(farm, FarmDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FarmDto> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farm -> modelMapper.map(farm, FarmDto.class))
                .toList();
    }

    @Transactional
    @Override
    public FarmDto updateFarmName(Long id, String name) {
        Farm farm = farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
        if (name != null) {
            farm.setCompanyName(name);
        }
        farmRepository.save(farm);
        return modelMapper.map(farm, FarmDto.class);
    }

    @Transactional
    @Override
    public boolean deleteFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
        farmRepository.delete(farm);
        return true;
    }
}
