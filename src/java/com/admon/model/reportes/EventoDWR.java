package com.admon.model.reportes;

import com.admon.bss.reportes.EventoBss;
import com.admon.model.BaseModel;

public class EventoDWR extends BaseModel{

    private EventoBss eventoBss;

    public EventoDWR() {
    }

    public void setEventoBss(EventoBss eventoBss) {
        this.eventoBss = eventoBss;
    }
    
}
