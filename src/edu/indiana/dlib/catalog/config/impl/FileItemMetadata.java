/**
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
package edu.indiana.dlib.catalog.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import edu.indiana.dlib.catalog.config.DataFormatException;
import edu.indiana.dlib.catalog.config.ItemMetadata;

/**
 * An extension of ItemMetadata that allows for the reading and writing
 * of ItemMetadata to a filesystem.
 */
public class FileItemMetadata extends ItemMetadata {

    public FileItemMetadata(File file) throws IOException, DataFormatException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            super.parseInputStream(fis);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
    
    public void write(File outputFile) throws IOException, ParserConfigurationException, TransformerException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFile);
            super.writeOutXML(fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        
    }

}
