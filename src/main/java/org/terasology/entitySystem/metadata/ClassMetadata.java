/*
 * Copyright 2013 MovingBlocks
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
package org.terasology.entitySystem.metadata;

import java.util.Collection;

/**
 * Class Metadata provides information on a class and its fields, and the ability to create, copy or manipulate an instance of the class.
 * @author Immortius
 */
public interface ClassMetadata<T> {

    /**
     * @return A collection of the names that identify this class
     */
    Collection<String> getNames();

    /**
     * @return The primary name that identifies this class
     */
    String getName();

    /**
     * @return The class described by this metadata
     */
    Class<T> getType();

    /**
     * @param id The previously set id of the field
     * @return The field identified by the given id, or null if there is no such field
     */
    FieldMetadata<T, ?> getField(int id);

    /**
     * @param name The name of the field
     * @return The field identified by the given name, or null if there is no such field
     */
    FieldMetadata<T, ?> getField(String name);

    /**
     * @return The fields that this class has.
     */
    Collection<FieldMetadata<T, ?>> getFields();

    /**
     * @return A new instance of this class.
     */
    T newInstance();

    /**
     * @param object The instance of this class to copy
     * @return A copy of the given object
     */
    T copy(T object);

    /**
     * @return The number of fields this class has
     */
    int getFieldCount();
}
