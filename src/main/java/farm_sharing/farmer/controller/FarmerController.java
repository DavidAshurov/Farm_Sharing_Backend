package farm_sharing.farmer.controller;

import farm_sharing.farmer.dto.FarmerDto;
import farm_sharing.farmer.dto.NewFarmerDto;
import farm_sharing.farmer.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmer")
@RequiredArgsConstructor
public class FarmerController {
    final FarmerService farmerService;

    @PostMapping
    public boolean register(@RequestBody NewFarmerDto newFarmerDto) {
        return farmerService.register(newFarmerDto);
    }

    @GetMapping("/{id}")
    public FarmerDto findById(@PathVariable Long id) {
        return farmerService.findById(id);
    }

    @GetMapping
    public List<FarmerDto> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @PutMapping("/{id}/{name}")
    public FarmerDto updateCompanyName(@PathVariable Long id,@PathVariable String name) {
        return farmerService.updateCompanyName(id,name);
    }

    @DeleteMapping ("/{id}")
    public boolean deleteFarmer(@PathVariable Long id) {
        return farmerService.deleteFarmer(id);
    }
}
