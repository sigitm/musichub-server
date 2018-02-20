package it.musichub.server.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import it.musichub.server.library.model.Song;
import it.musichub.server.upnp.model.Device;

public class RestDeviceMapper {

//	public static void main(String[] args) {
//		try {
//			Device device = new Device();
//			device.setUdn("fsdgffasfda");
//			device.setCustomName("giuiseppe");
//			device.setDeviceType("DOG");
//			
//		
//			DeviceIcon icon1 = new DeviceIcon("dog/prova", 12,13,128,new URI("http://a.b.it"), "Array".getBytes());
//			device.setIcons(new DeviceIcon[]{icon1});
//			
//			
//			DeviceIconDto deviceIconDto = toDto(icon1);
//			System.out.println("deviceIconDto="+deviceIconDto);
//			
//			
//			DeviceDto deviceDto = toDto(device);
//			System.out.println("deviceDto="+deviceDto);
//			
//			
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
	
//	public static Device fromDto(DeviceDto dto){
//		return getModelMapper().map(dto, Device.class);
//	}
	
	public static DeviceDto toDeviceDto(Device model){
		return getModelMapper().map(model, DeviceDto.class);
	}
	
	public static List<DeviceDto> toDeviceDto(List<Device> models){
		List<DeviceDto> modelsDto = new ArrayList<>();
		for (Device model : models)
			modelsDto.add(toDeviceDto(model));
		return modelsDto;
	}
	
//	public static DeviceIconDto toDto(DeviceIcon model){
//		return getModelMapper().map(model, DeviceIconDto.class);
//	}
	
	public static SongDto toSongDto(Song model){
		return getModelMapper().map(model, SongDto.class);
	}
	
	public static List<SongDto> toSongDto(List<Song> models){
		List<SongDto> modelsDto = new ArrayList<>();
		for (Song model : models)
			modelsDto.add(toSongDto(model));
		return modelsDto;
	}
	
	private static ModelMapper getModelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Device, DeviceDto> deviceOrderMap = new PropertyMap<Device, DeviceDto>() {
			  protected void configure() {
			    map().setId(source.getUdn());
			  }
		};
		modelMapper.addMappings(deviceOrderMap);
		PropertyMap<Song, SongDto> songOrderMap = new PropertyMap<Song, SongDto>() {
			  protected void configure() {
			    map().setFolderName(source.getFolder().getName()); //TODO XXXX serviva o ci arrivava da solo?
			    map().setAlbumTitle(source.getAlbumTitle());
			  }
		};
		modelMapper.addMappings(songOrderMap);
		Converter<byte[], String> toBase64 = new AbstractConverter<byte[], String>() {
			  protected String convert(byte[] source) {
			    return source == null ? null : DatatypeConverter.printBase64Binary(source);
			  }
			};
		modelMapper.addConverter(toBase64);
		return modelMapper;
	}
}