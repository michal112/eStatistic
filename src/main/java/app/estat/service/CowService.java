package app.estat.service;

import app.estat.model.entity.Cow;
import app.estat.model.repository.CowRepository;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mszubert on 26.10.15.
 */
@Service
public class CowService extends AbstractService<CowRepository, Cow> {}
