package app.estat.web.service;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowParentRepository;

import org.springframework.stereotype.Service;

@Service
public class CowParentService extends AbstractEntityService<CowParentRepository, CowParent> {}
