package app.estat.web.service;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.repository.BullRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BullService extends AbstractEntityService<BullRepository, Bull> {}
