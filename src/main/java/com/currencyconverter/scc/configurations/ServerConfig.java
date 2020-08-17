package com.currencyconverter.scc.configurations;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.HotReload;

@HotReload
@Sources("file:ServerConfig.properties")
public interface ServerConfig extends Config {
    String hostname();
}
