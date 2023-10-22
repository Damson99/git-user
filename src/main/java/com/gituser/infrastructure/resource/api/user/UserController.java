package com.gituser.infrastructure.resource.api.user;

import com.gituser.application.user.GetUsernameCommand;
import com.gituser.application.user.UserApplicationService;
import com.gituser.domain.user.GitUser;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, ApiVersion.V1_JSON})
@RequiredArgsConstructor
class UserController {
    private final UserApplicationService userApplicationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{username}")
    public ResponseEntity<GitUser> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userApplicationService.handle(new GetUsernameCommand(username)));
    }
}
