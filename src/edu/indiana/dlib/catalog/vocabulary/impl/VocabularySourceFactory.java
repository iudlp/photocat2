/*
 * Copyright 2011, Trustees of Indiana University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *   
 *   Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *   
 *   Neither the name of Indiana University nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *   
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. 
 */
package edu.indiana.dlib.catalog.vocabulary.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.indiana.dlib.catalog.config.FieldConfiguration;
import edu.indiana.dlib.catalog.config.Definitions;
import edu.indiana.dlib.catalog.config.SourceDefinition;
import edu.indiana.dlib.catalog.config.VocabularySourceConfiguration;
import edu.indiana.dlib.catalog.vocabulary.VocabularySource;
import edu.indiana.dlib.catalog.vocabulary.VocabularySourceInitializationException;
import edu.indiana.dlib.catalog.vocabulary.VocabularySourceManager;

public class VocabularySourceFactory {

    private static VocabularySourceFactory INSTANCE;
    
    public static VocabularySourceFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VocabularySourceFactory();
        }
        return INSTANCE;
    }
    
    private VocabularySourceManager manager;
    
    public void setVocabularySourceManager(VocabularySourceManager manager) {
        if (this.manager != null) {
            throw new IllegalStateException("VocabularySourceManager has already been set for this instance!");
        }
        this.manager = manager;
    }

    public List<VocabularySource> getVocabularySources(FieldConfiguration fieldConfig, Definitions definitions) throws IOException, VocabularySourceInitializationException {
        if (this.manager == null) {
            throw new IllegalStateException("No VocabularySourceManager has been associated with this VocabularySourceFactory!");
        }
        ArrayList<VocabularySource> sources = new ArrayList<VocabularySource>();
        if (fieldConfig.getVocabularySources() != null) {
            for (VocabularySourceConfiguration config : fieldConfig.getVocabularySources()) {
                sources.add(manager.getVocabularySource(config, definitions.getSourceDefinition(config.getType())));
            }
        }
        return sources;
    }
    
    public VocabularySource getVocabularySource(VocabularySourceConfiguration config, SourceDefinition def) throws IOException, VocabularySourceInitializationException {
        if (this.manager == null) {
            throw new IllegalStateException("No VocabularySourceManager has been associated with this VocabularySourceFactory!");
        }
        return manager.getVocabularySource(config, def);
    }
    
}
