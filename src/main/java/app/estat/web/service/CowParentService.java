package app.estat.web.service;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowParentRepository;

import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.response.CowParentResponse;
import org.springframework.stereotype.Service;

@Service
public class CowParentService extends AbstractEntityService<CowParentRepository, CowParent,
        CowParentRequest, CowParentResponse> {}
