package com.waes.diffapp.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.dto.DiffRequestDto;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.service.DiffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "v1/diff",
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
public class ApiController {

    private final DiffService diffService;

    public ApiController(DiffService diffService) {
        this.diffService = diffService;
    }

    @PostMapping(value = "{sessionId}/{diffSide}")
    public ResponseEntity<String> uploadDiffContent(@PathVariable(value = "sessionId") final String sessionId,
                                                    @PathVariable(value = "diffSide") final DiffSide diffSide,
                                                    @Valid @RequestBody final DiffRequestDto diffRequestDto) {
        diffService.uploadContent(sessionId, diffSide, diffRequestDto.getContent());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "{sessionId}")
    public ResponseEntity<DiffResult> uploadDiffContent(@PathVariable(value = "sessionId") final String sessionId) {
       return ResponseEntity.ok(diffService.analyze(sessionId));
    }
}
