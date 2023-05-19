package pl.aogiri.ims.confirmation.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.confirmation.domain.converter.ConfirmationConverter;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationEntity;
import pl.aogiri.ims.confirmation.domain.repository.ConfirmationRepository;
import pl.aogiri.ims.confirmation.presentation.dto.ConfirmationUpsertRequest;

@Service
@Transactional
public class ConfirmationService extends BaseCrudService<ConfirmationEntity, ConfirmationUpsertRequest, Void, Void, Void> {

    public ConfirmationService(ConfirmationRepository repository, ConfirmationConverter converter) {
        super(repository, converter, null);
    }
}
