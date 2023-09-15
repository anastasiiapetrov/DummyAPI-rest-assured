package dto;

import lombok.*;

@Setter @AllArgsConstructor @NoArgsConstructor@Getter@Builder
public class CreateUserRequest {
    private String lastName;
    private String firstName;
    private String email;

}
