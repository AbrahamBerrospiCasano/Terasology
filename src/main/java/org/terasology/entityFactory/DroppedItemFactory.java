/*
 * Copyright 2013 Moving Blocks
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

package org.terasology.entityFactory;

import com.google.common.collect.Maps;
import org.terasology.logic.inventory.DroppedItemComponent;
import org.terasology.logic.common.lifespan.LifespanComponent;
import org.terasology.logic.location.LocationComponent;
import org.terasology.entitySystem.EntityManager;
import org.terasology.entitySystem.EntityRef;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.engine.CoreRegistry;
import org.terasology.logic.inventory.ItemComponent;
import org.terasology.model.inventory.Icon;
import org.terasology.rendering.logic.MeshComponent;
import org.terasology.rendering.primitives.Mesh;
import org.terasology.rendering.primitives.MeshFactory;

import javax.vecmath.Vector3f;
import java.util.Map;

/**
 * @author Adeon
 */
public class DroppedItemFactory {
    private EntityManager entityManager;
    private Map<String, Mesh> iconMeshes = Maps.newHashMap();

    public DroppedItemFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityRef newInstance(Vector3f location, String iconName, float lifespan) {
        return newInstance(location, iconName, lifespan, EntityRef.NULL);
    }

    public EntityRef newInstance(Vector3f location, String iconName, float lifespan, EntityRef placedEntity) {
        Mesh itemMesh = iconMeshes.get(iconName);
        if (itemMesh == null) {
            Icon icon = Icon.get(iconName);
            itemMesh = MeshFactory.getInstance().generateItemMesh(icon.getX(), icon.getY());
            iconMeshes.put(iconName, itemMesh);
        }

        Prefab prefab = CoreRegistry.get(PrefabManager.class).getPrefab("core:droppeditem");

        if (prefab != null && prefab.getComponent(LocationComponent.class) != null) {
            EntityRef itemEntity = entityManager.create(prefab, location);
            MeshComponent itemMeshComponent = itemEntity.getComponent(MeshComponent.class);
            /*if(itemEntity.hasComponent(BoxShapeComponent.class)){
                itemEntity.removeComponent(BoxShapeComponent.class);
            }
            HullShapeComponent hull = new HullShapeComponent();
            hull.sourceMesh = itemMesh;
            itemEntity.addComponent(hull);*/
            //HullShapeComponent
            itemMeshComponent.mesh = itemMesh;
            itemEntity.saveComponent(itemMeshComponent);

            LifespanComponent lifespanComp = itemEntity.getComponent(LifespanComponent.class);
            lifespanComp.lifespan = lifespan;
            itemEntity.saveComponent(lifespanComp);

            DroppedItemComponent droppedItem = itemEntity.getComponent(DroppedItemComponent.class);
            droppedItem.itemEntity = entityManager.copy(placedEntity);
            ItemComponent itemComponent = placedEntity.getComponent(ItemComponent.class);

            ItemComponent newItem = new ItemComponent();

            newItem.stackCount = 1;
            newItem.name = itemComponent.name;
            newItem.baseDamage = itemComponent.baseDamage;
            newItem.consumedOnUse = itemComponent.consumedOnUse;
            newItem.icon = itemComponent.icon;
            newItem.stackId = itemComponent.stackId;
            newItem.renderWithIcon = itemComponent.renderWithIcon;

            droppedItem.itemEntity.saveComponent(newItem);
            itemEntity.saveComponent(droppedItem);

            return itemEntity;
        }
        return EntityRef.NULL;
    }
}
