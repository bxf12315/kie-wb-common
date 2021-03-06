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

package org.kie.workbench.common.services.datamodeller.driver.impl.annotations;

import org.kie.workbench.common.services.datamodeller.core.AnnotationValuePairDefinition;
import org.kie.workbench.common.services.datamodeller.core.impl.AbstractAnnotationDefinition;
import org.kie.workbench.common.services.datamodeller.core.impl.AnnotationValuePairDefinitionImpl;

@Deprecated
public class RoleAnnotationDefinition extends AbstractAnnotationDefinition {

    public RoleAnnotationDefinition() {

        super(org.kie.api.definition.type.Role.class.getName(), true, false);
        addValuePair( new AnnotationValuePairDefinitionImpl( "value", org.kie.api.definition.type.Role.class.getName() + ".Type", AnnotationValuePairDefinition.ValuePairType.ENUM, "FACT" ) );
    }

    public static RoleAnnotationDefinition getInstance() {
        return new RoleAnnotationDefinition();
    }
}

