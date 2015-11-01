package app.estat.service;

import app.estat.model.entity.Lactation;
import app.estat.model.repository.LactationRepository;
import app.estat.model.request.LactationRequest;
import app.estat.model.response.LactationResonse;

import org.springframework.stereotype.Service;

@Service
public class LactationService extends AbstractEntityService<LactationRepository, Lactation,
            LactationRequest, LactationResonse> {}
