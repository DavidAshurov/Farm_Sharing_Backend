package farm_sharing.reservation.service;

import farm_sharing.reservation.dto.ReservationResultDto;

public interface ReservationService {
    ReservationResultDto createReservation(String userNickname);
}
