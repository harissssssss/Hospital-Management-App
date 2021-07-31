package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.capgemini.aarogyaNiketan.enums.UserType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthenticationRegisterRequest implements Serializable {
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    private String password;

    @NotNull
    private UserType userType;
}
