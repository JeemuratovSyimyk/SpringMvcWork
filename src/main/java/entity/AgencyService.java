package entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    public Agency saveAgency(Agency agency) throws AgencyExistsException {
        Optional<Agency> existingAgency = agencyRepository.findByName(agency.getName());
        if (existingAgency.isPresent()) {
            throw new AgencyExistsException("Agency with this name already exists");
        }
        return agencyRepository.save(agency);
    }

    public Optional<Agency> getAgencyById(Long id) {
        return agencyRepository.findById(id);
    }

    public void deleteAgency(Long id) {
        agencyRepository.deleteById(id);
    }
}