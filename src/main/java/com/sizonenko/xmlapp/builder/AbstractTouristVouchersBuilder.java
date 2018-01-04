package com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.entity.Request;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

public abstract class AbstractTouristVouchersBuilder {
    public static final Logger LOGGER = Logger.getLogger(AbstractTouristVouchersBuilder.class);
    protected Set<Request> requests;

    public AbstractTouristVouchersBuilder(){
        requests = new HashSet<>();
    }

    public AbstractTouristVouchersBuilder(Set<Request> requests){
        this.requests = requests;
    }

    public Set<Request> getRequests(){
        return requests;
    }

    abstract  public void buildSetRequests(String filename);
}
