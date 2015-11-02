package app.estat.service;

import app.estat.model.entity.Cow;
import app.estat.model.repository.CowRepository;

import app.estat.model.request.CowRequest;
import app.estat.model.response.CowResponse;
import org.springframework.stereotype.Service;

@Service
public class CowService extends AbstractEntityService<CowRepository, Cow, CowRequest, CowResponse> {}
