package app.estat.web.controller;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.response.CowParentResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentController extends AbstractController<CowParent, CowParentRequest, CowParentResponse> {}
