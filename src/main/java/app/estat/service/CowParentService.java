package app.estat.service;

import app.estat.model.entity.CowParent;
import app.estat.model.repository.CowParentRepository;

import app.estat.model.request.CowParentRequest;
import app.estat.model.response.CowParentResponse;
import org.springframework.stereotype.Service;

@Service
public class CowParentService extends AbstractEntityService<CowParentRepository, CowParent,
            CowParentRequest, CowParentResponse> {}
