package student.record.web.rest.vm;

import student.record.model.User;
import student.record.service.dto.UserDTO;


public class ManagedUserVM extends UserDTO {

    private Long id;

    public ManagedUserVM() {
    }

    public ManagedUserVM(User user) {
        super(user);
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
