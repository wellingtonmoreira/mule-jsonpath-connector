package org.wmoreira.jsonpath;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.wmoreira.jsonpath.config.ConnectorConfig;
import org.wmoreira.jsonpath.exception.InvalidJsonPathException;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

@Connector(name="jsonpath-connector", friendlyName="Jsonpath")
public class JsonpathConnector {

    @Config
    ConnectorConfig config;

    @Processor
    public String apply(final String value, final String expression) {
    	try {
    		return JsonPath.parse(value).read(expression).toString();
    	} catch (final InvalidPathException ipe) {
    		throw new InvalidJsonPathException(ipe);
    	}
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}