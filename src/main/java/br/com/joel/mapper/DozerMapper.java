package br.com.joel.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <Origin, Destiny> Destiny parseObject(Origin origin, Class<Destiny> destination ) {
		return mapper.map(origin, destination);
	}
	
	public static <Origin, Destiny> List<Destiny> parseListObjects(List<Origin> origins, Class<Destiny> destination ) {
		List<Destiny> destinationObjects = new ArrayList<Destiny>();
		
		for (Origin origin : origins) {
			destinationObjects.add(mapper.map(origin, destination));
		}
		return destinationObjects;
	}

}
