package app.estat.web.service;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowParentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CowParentService extends AbstractEntityService<CowParentRepository, CowParent> {}
