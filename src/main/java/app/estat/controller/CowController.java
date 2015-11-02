package app.estat.controller;

import app.estat.model.request.CowRequest;
import app.estat.model.response.CowResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowController extends AbstractController<CowRequest, CowResponse> {}
