package rest.allianz.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import allianz.entities.AllianzDto;
import allianz.webservice.business.AllianzEntitiesHandler;

@RestController
@RequestMapping("/allianz")
public class AllianzController {

	static List<AllianzDto> allianzDtoList;
	static {
		allianzDtoList = AllianzEntitiesHandler.initSession();
	}

	@GetMapping("/file/content")
    public ResponseEntity < List<AllianzDto> > getFileContent() {
		return ResponseEntity.ok().body(allianzDtoList);
    }

	@PutMapping("/add/entity")
    public ResponseEntity < String > addEntity(@RequestBody AllianzDto allianzDto) {
		return ResponseEntity.ok().body(AllianzEntitiesHandler.addAllianzDto(allianzDtoList, allianzDto));
    }

	@PostMapping("/update/entity")
    public ResponseEntity < String > updateEntity(@RequestBody AllianzDto allianzDto) {
		return ResponseEntity.ok().body(AllianzEntitiesHandler.updateAllianzDto(allianzDtoList, allianzDto));
    }

	@DeleteMapping("/delete/entity/{id}")
    public ResponseEntity < String > deleteEntity(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(AllianzEntitiesHandler.deleteAllianzDto(allianzDtoList, id));
    }

	@GetMapping("/get/entity/{id}")
    public ResponseEntity < AllianzDto > getPatientById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(AllianzEntitiesHandler.getEntityById(allianzDtoList, id));
    }

}
