package farm_sharing.farm.controller;

import farm_sharing.farm.dto.FarmDto;
import farm_sharing.farm.dto.NewFarmDto;
import farm_sharing.farm.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farm")
@RequiredArgsConstructor
public class FarmController {
    final FarmService farmService;

    @PostMapping
    public boolean register(@RequestBody NewFarmDto dto) {
        return farmService.register(dto);
    }

    @GetMapping("/{id}")
    public FarmDto findById(@PathVariable Long id) {
        return farmService.findById(id);
    }

    @GetMapping
    public List<FarmDto> getAllFarms() {
        return farmService.getAllFarms();
    }

    @PutMapping("/{id}/{name}")
    public FarmDto updateFarmName(@PathVariable Long id, @PathVariable String name) {
        return farmService.updateFarmName(id,name);
    }

    @DeleteMapping ("/{id}")
    public boolean deleteFarm(@PathVariable Long id) {
        return farmService.deleteFarm(id);
    }
}
