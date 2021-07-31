package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthenticationLoginRequest {
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    private String password;
}
