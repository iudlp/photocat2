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
package edu.indiana.dlib.catalog.search.impl.click.control;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.Panel;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;

public class QuickSearchPanel extends Panel implements ActionListener {
    
    protected Form quickSearchForm;
    
    protected TextField queryTextField;
    
    public QuickSearchPanel() {
        this("quickSearchPanel", "quickSearchPanel", "quick-search-panel.htm", "search.htm");
    }
    
    public QuickSearchPanel(String name, String id, String template, String actionUrl) {
        super(name, id, template);
        
        quickSearchForm = new Form("quickSearchForm");
        quickSearchForm.setActionURL(actionUrl);
        queryTextField = new TextField("query");
        queryTextField.setWidth("16em");
        quickSearchForm.add(queryTextField);
        quickSearchForm.add(new Submit("search", getMessage("submit-search"), this, "onAction"));
        //quickSearchForm.setActionListener(this);
        
        this.add(quickSearchForm);
    }
    
    public void onRender() {
        super.onRender();
        queryTextField.setValue(getMessage("search-box-text"));
        queryTextField.setAttribute("onfocus", "clearValue(this, '" + getMessage("search-box-text") + "');");
    }
    
    
    public List<Element> getHeadElements() {
        // We use lazy loading to ensure the CSS import is only added the 
        // first time this method is called. 
        if (headElements == null) { 
            // Get the head elements from the super implementation 
            headElements = super.getHeadElements(); 

            CssImport cssImport = new CssImport("/css/quick-search-panel.css");
            headElements.add(cssImport);
            
            JsImport jsImport = new JsImport("/js/quick-search-panel.js");
            headElements.add(jsImport);
        } 
        return headElements; 
    }
    
    public boolean onAction() {
        try {
            getPage().setRedirect("search.htm?form_name=searchForm&query=" + URLEncoder.encode(queryTextField.getValue(), "UTF-8") + "&pageSize=20&action=Search");
        } catch (UnsupportedEncodingException ex) {
            getPage().setRedirect("search.htm?form_name=searchForm&query=" + URLEncoder.encode(queryTextField.getValue()) + "&pageSize=20&action=Search");
        }
        return true;
    }
    
    public boolean onAction(Control source) {
        return onAction();
    }

}
