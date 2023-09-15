package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnsuccessCreateUserResponse {
    private String email;
    private String firstName;
    private String lastName;
}
