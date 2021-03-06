/**
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.services.datamodeller.core;

import java.util.List;
import java.util.Set;

public interface DataModel {

    Set<DataObject> getDataObjects();

    Set<DataObject> getDataObjects( ObjectSource source );

    /**
     * The created data object will have the class name "packageName.name"
     * @param packageName The package to locate the DataObject.
     * @param name
     * @return the created DataObject
     */
    DataObject addDataObject( String packageName, String name );

    DataObject addDataObject( String packageName, String name, Visibility visibility );

    DataObject addDataObject( String packageName, String name, Visibility visibility, boolean isAbstract, boolean isFinal );

    DataObject addDataObject( String packageName, String name, Visibility visibility, boolean isAbstract, boolean isFinal, ObjectSource source );

    DataObject addDataObject( String className, Visibility visibility, boolean isAbstract, boolean isFinal, ObjectSource source );

    DataObject addDataObject( String className, Visibility visibility, boolean isAbstract, boolean isFinal );

    DataObject addDataObject( String className );

    DataObject addDataObject( DataObject dataObject );

    DataObject getDataObject( String className );

    DataObject addDataObject( String packageName, String name, ObjectSource source );

    DataObject addDataObject( String className, ObjectSource source );

    DataObject getDataObject( String className, ObjectSource source );

    DataObject removeDataObject( String className );

    DataObject removeDataObject( String className, ObjectSource source );

    //TODO this method was added for refactoring purposes, check if this I should maintain this method
    int getId();

    //TODO this method was added for refactoring purposes, check if this I should maintain this method
    public List<DataObject> getExternalClasses();


    /**
     *
     * @param className
     *
     * @return  true if the given class name is an imported class.
     */
    boolean isExternal( String className );


}