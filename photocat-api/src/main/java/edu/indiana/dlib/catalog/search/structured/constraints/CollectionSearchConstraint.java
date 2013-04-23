/**
 * Copyright 2015 Trustees of Indiana University. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE TRUSTEES OF INDIANA UNIVERSITY ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE TRUSTEES OF INDIANA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of the Trustees of Indiana University.
 */
package edu.indiana.dlib.catalog.search.structured.constraints;

import edu.indiana.dlib.catalog.config.CollectionConfiguration;

public class CollectionSearchConstraint extends SerializableSearchConstraint {

    private String collectionId;
    
    private String collectionName;

    private boolean implicit;
    
    public CollectionSearchConstraint(String id) {
        collectionId = id;
        collectionName = id;
    }
    
    public CollectionSearchConstraint(String id, String name) {
        collectionId = id;
        collectionName = name;
    }
    
    public CollectionSearchConstraint(CollectionConfiguration conf, boolean implicit) {
        collectionId = conf.getId();
        collectionName = conf.getCollectionMetadata().getFullName();
        this.implicit = implicit;
    }
    
    public String getCollectionId() {
        return collectionId;
    }
    
    public String getCollectionName() {
        return collectionName;
    }
    
    public String getDisplay() {
        return "collection: " + collectionName;
    }

    public boolean isImplicit() {
        return implicit;
    }
    
    public boolean equals(Object o) {
        if (o instanceof CollectionSearchConstraint) {
            CollectionSearchConstraint other = (CollectionSearchConstraint) o;
            return (equal(other.collectionId, collectionId) && equal(other.collectionName, collectionName) && implicit == other.implicit);
        } else {
            return false;
        }
    }
    
    public int hashCode() {
        return new String(collectionId + collectionName + implicit).hashCode();
    }

}
