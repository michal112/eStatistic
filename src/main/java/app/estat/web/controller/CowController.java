package app.estat.web.controller;

import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowController extends AbstractController<CowRequest, CowResponse> {}
