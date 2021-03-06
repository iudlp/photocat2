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
package edu.indiana.dlib.catalog.asynchronous;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A user-owned class that manages asynchronous operations.  The
 * current implementation allows one operation to be performed at
 * a time.
 */
public class UserOperationManager {
    
    public static UserOperationManager getOperationManager(HttpServletRequest request, String userId) {
        return getOperationManager(request.getSession(), userId);
    }
    
    public static UserOperationManager getOperationManager(HttpSession session, String userId) {
        UserOperationManager manager = (UserOperationManager) session.getAttribute(UserOperationManager.class.getName());
        if (manager == null) {
            manager = new UserOperationManager(session.getServletContext(), userId);
            session.setAttribute(UserOperationManager.class.getName(), manager);
        }
        return manager;
    }

    private OperationManagerContextListener manager;
    
    private String userId;
    
    private UserOperationManager(ServletContext sc, String userId) {
        manager = OperationManagerContextListener.getOperationManager(sc);
        this.userId = userId;
    }
    
    /**
     * Adds the given operation to the queue of operations to be 
     * performed by this UserOperationManager.  The current
     * implementation will start working on the operation immediately
     * unless there are other Operations in the queue before it.
     */
    public void queueOperation(Operation operation) {
        manager.queueOperation(operation, userId);
    }
    
    /**
     * Lists operations that either haven't run yet or are
     * currently running.
     */
    public List<Operation> listIncompleteOperations() {
        return manager.listIncompleteOperations(userId);
    }

}
