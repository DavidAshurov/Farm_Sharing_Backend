package farm_sharing.reservation.controller;

import farm_sharing.reservation.dto.ReservationResultDto;
import farm_sharing.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
     final ReservationService reservationService;

     @PostMapping
     public ReservationResultDto createReservation(Principal principal) {
         return reservationService.createReservation(principal.getName());
     }
}
