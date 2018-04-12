package cabare.service;

import cabare.data.CabareRepository;
import cabare.entity.model.Cabare;
import cabare.exception.CabareNotFoundException;
import cabare.exception.CabareNotSpecifiedException;
import org.springframework.beans.factory.annotation.Autowired;

public class CabareServiceImpl implements CabareService {
    @Autowired
    private CabareRepository cabareRepository;

    @Override
    public Cabare findById(Long cabareId) {
        if (cabareId == null) {
            throw new CabareNotSpecifiedException();
        }
        return cabareRepository.findById(cabareId).orElseThrow(() -> new CabareNotFoundException());
    }
}
