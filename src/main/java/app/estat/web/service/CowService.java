package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.repository.CowRepository;

import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowResponse;
import org.springframework.stereotype.Service;

@Service
public class CowService extends AbstractEntityService<CowRepository, Cow, CowRequest, CowResponse> {}
