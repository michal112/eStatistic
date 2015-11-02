package app.estat.controller;

import app.estat.model.request.CowParentRequest;
import app.estat.model.response.CowParentResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentController extends AbstractController<CowParentRequest, CowParentResponse> {}
