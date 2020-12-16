package com.waes.diffapp.service.impl;

import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.repository.ObjectRepository;
import com.waes.diffapp.service.AbstractDiffService;
import com.waes.diffapp.validation.groups.ChainValidationGroup;
import com.waes.diffapp.validation.groups.CustomChainValidationGroup;
import org.springframework.stereotype.Service;

@Service
public class CustomDiffService extends AbstractDiffService {
    public CustomDiffService(ObjectRepository<String, DiffEntity> objectRepository) {
        super(objectRepository);
    }

    @Override
    public ChainValidationGroup validationChainToExecute() {
        return new CustomChainValidationGroup();
    }
}
