package student.record.web.rest.vm;

import student.record.model.Student;
import student.record.service.dto.UserDTO;

public class ManagedUserVM extends UserDTO {

    private Long id;

    private String name;

    public ManagedUserVM(Student student) {
        super(student.getUser());
        this.id = student.getId();
        this.name = student.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
