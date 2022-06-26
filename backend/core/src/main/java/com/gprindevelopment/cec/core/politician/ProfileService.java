package com.gprindevelopment.cec.core.politician;

import com.gprindevelopment.cec.core.externalapi.camara.CamaraAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CamaraAPI camaraAPI;

    public List<Profile> findAllOnCurrentLegislatureByPoliticianName(String name, String stateInitials) {
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, camaraAPI.requestCurrentLegislature().getId(), stateInitials);
    }

    public List<Profile> findAllByPoliticianName(String name, Long legislatureId) {
        return camaraAPI.requestProfilesByNameAndLegislatureId(name, legislatureId);
    }
}
