package com.waes.diffapp.service;

import java.util.Optional;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.exception.EntityNotFoundException;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.repository.ObjectRepository;
import com.waes.diffapp.validation.groups.ChainValidationGroup;

/**
 * Diff Service interface
 *
 * @author HS
 */
public abstract class AbstractDiffService implements DiffService {

    private final ObjectRepository<String, DiffEntity> objectRepository;

    public AbstractDiffService(ObjectRepository<String, DiffEntity> objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Override
    public void uploadContent(String sessionId, DiffSide diffSide, String content) {
        Optional<DiffEntity> c = objectRepository.get(sessionId);
        DiffEntity diffEntity = c
            .orElseGet(() -> objectRepository.add(sessionId, new DiffEntity()));

        diffEntity.setSideContent(diffSide, content);
    }

    @Override
    public DiffResult analyze(String sessionId) {
        DiffEntity diffEntity = objectRepository.get(sessionId)
            .orElseThrow(() -> new EntityNotFoundException("Diff entity not found for sessionId: " + sessionId));

        return validationChainToExecute().execute(diffEntity, new DiffResult());
    }

    public abstract ChainValidationGroup validationChainToExecute();
}
