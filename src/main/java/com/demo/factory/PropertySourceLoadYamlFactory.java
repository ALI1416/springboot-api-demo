package com.demo.factory;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;

/**
 * 用于@PropertySource加载yml配置文件。
 */
public class PropertySourceLoadYamlFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		return new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource()).get(0);
	}
}