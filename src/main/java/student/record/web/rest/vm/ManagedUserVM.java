package student.record.web.rest.vm;

import student.record.model.Student;
import student.record.service.dto.LinkDTO;
import student.record.service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ManagedUserVM extends UserDTO {

    private Long id;

    private String name;

    private List<LinkDTO> links;

    public ManagedUserVM(Student student) {
        super(student.getUser());
        this.id = student.getId();
        this.name = student.getName();
        if (student.getLinks() != null) {
            this.links = student.getLinks().stream().map(link -> {
                LinkDTO linkDTO = new LinkDTO();
                linkDTO.setUrl(link.getUrl());
                return linkDTO;
            }).collect(Collectors.toList());
        }
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

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }

}
