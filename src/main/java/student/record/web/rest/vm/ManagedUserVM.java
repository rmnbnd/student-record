package student.record.web.rest.vm;

import student.record.model.Link;
import student.record.model.Student;
import student.record.service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ManagedUserVM extends UserDTO {

    private Long id;

    private String name;

    private List<String> links;

    public ManagedUserVM(Student student) {
        super(student.getUser());
        this.id = student.getId();
        this.name = student.getName();
        this.links = student.getLinks().stream().map(Link::getUrl).collect(Collectors.toList());
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

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
