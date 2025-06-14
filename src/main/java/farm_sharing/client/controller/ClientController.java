package farm_sharing.client.controller;

import org.springframework.web.bind.annotation.*;
import farm_sharing.client.dto.ClientDto;
import farm_sharing.client.dto.NewClientDto;
import farm_sharing.client.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public boolean addClient(@RequestBody NewClientDto newClientDto) {
        return clientService.addClient(newClientDto);
    }

    @GetMapping("/{id}")
    public ClientDto findClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}/name")
    public ClientDto updateClientName(@PathVariable Long id, @RequestBody String name) {
        return clientService.updateClientName(id, name);
    }

    @PutMapping("/{id}/phone")
    public ClientDto updateClientPhoneNumber(@PathVariable Long id, @RequestBody String phoneNumber) {
        return clientService.updateClientPhoneNumber(id, phoneNumber);
    }

    @PutMapping("/{id}/city")
    public ClientDto updateClientCity(@PathVariable Long id, @RequestBody String city) {
        return clientService.updateClientCity(id, city);
    }

    @PutMapping("/{id}/email")
    public ClientDto updateClientEmail(@PathVariable Long id, @RequestBody String email) {
        return clientService.updateClientEmail(id, email);
    }

    @DeleteMapping("/{id}")
    public ClientDto removeClient(@PathVariable Long id) {
        return clientService.removeClient(id);
    }

    @GetMapping("/city/{city}")
    public List<ClientDto> findClientsByCity(@PathVariable String city) {
        return clientService.findClientsByCity(city);
    }

    @GetMapping("/name/{name}")
    public List<ClientDto> findClientsByName(@PathVariable String name) {
        return clientService.findClientsByName(name);
    }

    @GetMapping("/phone/{phoneNumber}")
    public List<ClientDto> findClientsByPhoneNumber(@PathVariable String phoneNumber) {
        return clientService.findClientsByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public List<ClientDto> findClientsByEmail(@PathVariable String email) {
        return clientService.findClientsByEmail(email);
    }

    @GetMapping
    public List<ClientDto> findAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable Long id) {
        return clientService.existsById(id);
    }
}
