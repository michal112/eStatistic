package app.estat.service;

import app.estat.model.entity.Cow;
import app.estat.model.repository.CowRepository;

import org.springframework.stereotype.Service;

@Service
public class CowService extends AbstractService<CowRepository, Cow> {}
