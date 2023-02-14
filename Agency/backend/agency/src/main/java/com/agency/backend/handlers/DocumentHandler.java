package com.agency.backend.handlers;

import com.agency.backend.model.BaseIndexUnit;

import java.io.File;

public abstract class DocumentHandler {

    public abstract String getTextContent(File file);
}
