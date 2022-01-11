package allianz.webservice.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import allianz.entities.AllianzDto;

public class AllianzEntitiesHandler {

	public static List<AllianzDto> initSession() {
		List<AllianzDto> allianzDtoList;
		 InputStream inJson = AllianzDto.class.getResourceAsStream(WebserviceConstants.JSON_FILE_PATH);
		 try {
			 allianzDtoList = new ObjectMapper().readValue(inJson, new TypeReference<List<AllianzDto>>(){});
		} catch (Exception e) {
			allianzDtoList =  new ArrayList<>();
		}
		return allianzDtoList;
	}

	public static String addAllianzDto(List<AllianzDto> allianzDtoList, AllianzDto allianzDto) {
		checkAllianzDto(allianzDto);
		Optional<AllianzDto> optAllianzDto = allianzDtoList.stream().filter(streamAllianzDto -> streamAllianzDto.getId().equals(allianzDto.getId())).findFirst();
		if (!optAllianzDto.isPresent()) {
			allianzDtoList.add(allianzDto);
			return WebserviceConstants.ADD_ENTITY_SUCCESS_MESSAGE;
		}
		return WebserviceConstants.ADD_ENTITY_ERROR_MESSAGE;
	}

	public static String updateAllianzDto(List<AllianzDto> allianzDtoList, AllianzDto allianzDto) {
		checkAllianzDto(allianzDto);
		Optional<AllianzDto> optAllianzDto = allianzDtoList.stream().filter(streamAllianzDto -> streamAllianzDto.getId().equals(allianzDto.getId())).findFirst();
		if (optAllianzDto.isPresent()) {
			AllianzDto foundAllianzDto = optAllianzDto.get();
			foundAllianzDto.setDate(allianzDto.getDate());
			foundAllianzDto.setLabel(allianzDto.getLabel());
			return WebserviceConstants.UPDATE_ENTITY_SUCCESS_MESSAGE;
		}
		return WebserviceConstants.UPDATE_ENTITY_ERROR_MESSAGE;
	}

	public static String deleteAllianzDto(List<AllianzDto> allianzDtoList, Long id) {
		Optional<AllianzDto> optAllianzDto = allianzDtoList.stream().filter(streamAllianzDto -> streamAllianzDto.getId().equals(id)).findFirst();
		if (optAllianzDto.isPresent()) {
			allianzDtoList.remove(optAllianzDto.get());
			return WebserviceConstants.DELETE_ENTITY_SUCCESS_MESSAGE;
		}
		return WebserviceConstants.DELETE_ENTITY_ERROR_MESSAGE;
	}

	public static AllianzDto getEntityById(List<AllianzDto> allianzDtoList, Long id) {
		Optional<AllianzDto> optAllianzDto = allianzDtoList.stream().filter(streamAllianzDto -> streamAllianzDto.getId().equals(id)).findFirst();
		if (optAllianzDto.isPresent()) {
			return optAllianzDto.get();
		}
		return null;
	}
	
	private static void checkAllianzDto (AllianzDto allianzDto) {
		if (null == allianzDto || null == allianzDto.getId() || null == allianzDto.getLabel() || null == allianzDto.getDate()) {
			throw new RuntimeException(WebserviceConstants.MANDATORY_FIELD_ERROR);
		}
	}

}
